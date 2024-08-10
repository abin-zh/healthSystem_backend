package com.cykj.util.tree;

import cn.hutool.extra.mail.MailUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码工具类
 *
 * @author abin
 * @date 2024/6/26 22:41
 */
public class CaptchaUtils {
    private static final String STRS = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    private static final Random RANDOM = new Random();


    /**
     * 生成随机验证码
     *
     * @param length 验证码长度
     * @return 验证码
     */
    public static String generateCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int codeIndex = RANDOM.nextInt(STRS.length());
            stringBuilder.append(STRS.charAt(codeIndex));
        }
        return stringBuilder.toString();
    }

    /**
     * 生成随机颜色
     *
     * @return 随机颜色
     */
    private static Color generateRandomColor() {
        return new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256));
    }

    /**
     * 绘制验证码图片
     *
     * @param width   绘制图片长度
     * @param height  绘制图片宽读
     * @param lineNum
     * @param code
     * @return
     */
    public static BufferedImage createCodeImage(int width, int height, int lineNum, String code) {
        //空白图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘画引擎
        Graphics2D graphics = image.createGraphics();
        //背景色
        graphics.setColor(Color.WHITE);
        //绘制矩形
        graphics.fillRect(0, 0, width, height);

        int codeWidth = width / (code.length() + 1);
        int y = height * 3 / 4;

        //绘制验证码
        for (int i = 0; i < code.length(); i++) {
            //单字颜色
            graphics.setColor(generateRandomColor());
            Font codeFont = new Font(null, Font.BOLD + Font.ITALIC, 25);

            //角度偏移
            int angle = RANDOM.nextInt(35);
            //偏移方向
            angle = RANDOM.nextBoolean() == true ? angle : -angle;
            AffineTransform affineTransform = new AffineTransform();
            //Math.toRadians() 角度转弧度
            affineTransform.rotate(Math.toRadians(angle), 0, 0);
            codeFont = codeFont.deriveFont(affineTransform);
            graphics.setFont(codeFont);

            int x = (i * codeWidth) + (codeWidth / 2);
            graphics.drawString("" + code.charAt(i), x, y);
        }

        //干扰线
        for (int i = 0; i < lineNum; i++) {
            graphics.setColor(generateRandomColor());
            graphics.drawLine(RANDOM.nextInt(width), RANDOM.nextInt(height), RANDOM.nextInt(width), RANDOM.nextInt(height));
        }
        return image;
    }

    /**
     * 发送验证码图片
     *
     * @param response
     * @param session
     */
    public static void sendHttp(HttpServletResponse response, HttpSession session) {
        String code = CaptchaUtils.generateCode(4);
        //获取图片
        BufferedImage codeImage = CaptchaUtils.createCodeImage(120, 40, 5, code);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //code存入session，JSESSIONID添加cookie
        session.setAttribute("code", code);
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        response.setContentType("image/png");
        response.addCookie(cookie);
        try {
            ImageIO.write(codeImage, "png", baos);
            response.getOutputStream().write(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送邮箱验证码
     *
     * @param email
     */
    public static boolean sendSmtp(String email, HttpSession session, HttpServletResponse response) {
        String code = generateCode(6);
        //code存入session，JSESSIONID添加cookie
        session.setAttribute("code", code);
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        response.addCookie(cookie);
        try {
            String send = MailUtil.send(email, "体检系统登录验证码", "欢迎登录体检系统，您的登录验证码是<p style='color:red;'>"+ code +"</p>", true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
