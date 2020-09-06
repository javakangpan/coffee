package com.liting.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 康盼Java开发工程师
 * <p>
 * 封装响应对象
 * @date 2020/08/01
 */
public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200ok\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
