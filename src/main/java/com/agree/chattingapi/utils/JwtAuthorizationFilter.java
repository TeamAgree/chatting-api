package com.agree.chattingapi.utils;

import com.agree.chattingapi.conf.AuthConstants;
import com.agree.chattingapi.responses.CommonResponse;
import com.agree.chattingapi.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {

        if(request.getRequestURI().startsWith("/api/v1/public")){
            chain.doFilter(request, response);
            return;
        }

        // 3. OPTIONS 요청일 경우 => 로직 처리 없이 다음 필터로 이동
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        // [STEP1] Client에서 API를 요청할때 Header를 확인합니다.
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        logger.debug("[+] header Check: " + header);

        try {
            // [STEP2-1] Header 내에 토큰이 존재하는 경우
            if (header != null && !header.equalsIgnoreCase("")) {

                // [STEP2] Header 내에 토큰을 추출합니다.
                String token = TokenUtils.getTokenFromHeader(header);

                int validToken = TokenUtils.isValidToken(token);

                // [STEP3] 추출한 토큰이 유효한지 여부를 체크합니다.
                switch(validToken){
                    case 0:
                        // [STEP4] 토큰을 기반으로 사용자 아이디를 반환 받는 메서드
                        String userId = TokenUtils.getUserIdFromToken(token);
                        String roles = TokenUtils.getRolesFromToken(token);
                        logger.debug("[+] userId Check: " + userId);

                        // [STEP5] 사용자 아이디가 존재하는지 여부 체크
                        if (userId != null && !userId.equalsIgnoreCase("")) {
                            if(request.getRequestURI().startsWith("/api/v1/admin")){
                                if(roles.equals("ADMIN")){
                                    chain.doFilter(request, response);
                                }else {
                                    throw new CustomizedException("Only admin can access");
                                }
                            }
                            chain.doFilter(request, response);
                        } else {
                            throw new CustomizedException("TOKEN isn't userId");
                        }
                        break;
                    case 1:
                        String refreshToken = userService.getCookie(request);
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        PrintWriter printWriter = response.getWriter();

                        if(TokenUtils.isValidToken(refreshToken) == 0){
                            String accessToken = TokenUtils.reGenerateAccessToken(refreshToken);
                            printWriter.print(new ObjectMapper().writeValueAsString(
                                    new CommonResponse<>("02", "re-generate token", accessToken)
                            ));
                            printWriter.flush();
                            printWriter.close();
                            return;
                        }else {
                            printWriter.print(new ObjectMapper().writeValueAsString(
                                    new CommonResponse<>("Invalid refresh token",
                                            null))
                            );
                            printWriter.flush();
                            printWriter.close();
                            return;
                        }
                    default:
                        throw new CustomizedException("TOKEN is invalid");
                }
            }
            // [STEP2-1] 토큰이 존재하지 않는 경우
            else {
                throw new CustomizedException("Token is null");
            }
        } catch (Exception e) {
            // Token 내에 Exception이 발생 하였을 경우 => 클라이언트에 응답값을 반환하고 종료합니다.
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(jsonResponseWrapper(e));
            printWriter.flush();
            printWriter.close();
        }
    }

    /**
     * 토큰 관련 Exception 발생 시 예외 응답값 구성
     *
     * @param e Exception
     * @return JSONObject
     */
    private String jsonResponseWrapper(Exception e) throws JsonProcessingException {

        String resultMsg = "";
        // JWT 토큰 만료
        if (e instanceof ExpiredJwtException) {
            resultMsg = "TOKEN Expired";
        }
        // JWT 허용된 토큰이 아님
        else if (e instanceof SignatureException) {
            resultMsg = "TOKEN SignatureException Login";
        }
        // JWT 토큰내에서 오류 발생 시
        else if (e instanceof JwtException) {
            resultMsg = "TOKEN Parsing JwtException";
        }
        // 이외 JTW 토큰내에서 오류 발생
        else {
            resultMsg = "OTHER TOKEN ERROR";
        }

        CommonResponse<String> response = new CommonResponse<>("01", resultMsg, e.getMessage());
        logger.error(resultMsg, e);
        return new ObjectMapper().writeValueAsString(response);
    }
}
