package sg.darren.ms.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpServletRequestService {

    private final HttpServletRequest httpServletRequest;

    public String getClientAddress() {
        return httpServletRequest.getRemoteAddr();
    }

    public String getClientDetails() {
        return httpServletRequest.getHeader("User-Agent");
    }

    public String getClientOS() {
        try {
            final String osDetails = httpServletRequest.getHeader("User-Agent");
            final String lowerCaseOS = osDetails.toLowerCase();

            if (lowerCaseOS.contains("windows")) {
                return "Windows";
            } else if (lowerCaseOS.contains("iphone")) {
                return "iPhone";
            } else if (lowerCaseOS.contains("mac")) {
                return "Mac";
            } else if (lowerCaseOS.contains("x11")) {
                return "Unix";
            } else if (lowerCaseOS.contains("android")) {
                return "Android";
            } else if (lowerCaseOS.contains("postman")) {
                return "Postman";
            } else {
                return "UnKnown";
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String getClientBrowser() {
        try {
            final String browserDetails = httpServletRequest.getHeader("User-Agent");
            final String lowercaseBroswer = browserDetails.toLowerCase();

            String browser = "";
            if (lowercaseBroswer.contains("msie")) {
                String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
                browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
            } else if (lowercaseBroswer.contains("safari") && lowercaseBroswer.contains("version")) {
                browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
                        + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (lowercaseBroswer.contains("opr") || lowercaseBroswer.contains("opera")) {
                if (lowercaseBroswer.contains("opera"))
                    browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
                            + (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
                else if (lowercaseBroswer.contains("opr"))
                    browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                            .replace("OPR", "Opera");
            } else if (lowercaseBroswer.contains("chrome")) {
                browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
            } else if (lowercaseBroswer.contains("crios")) {
                browser = (browserDetails.substring(browserDetails.indexOf("CriOS")).split(" ")[0]).replace("/", "-");
            } else if ((lowercaseBroswer.indexOf("mozilla/7.0") > -1) || (lowercaseBroswer.indexOf("netscape6") != -1)
                    || (lowercaseBroswer.indexOf("mozilla/4.7") != -1) || (lowercaseBroswer.indexOf("mozilla/4.78") != -1)
                    || (lowercaseBroswer.indexOf("mozilla/4.08") != -1) || (lowercaseBroswer.indexOf("mozilla/3") != -1)) {
                browser = "Netscape-?";
            } else if (lowercaseBroswer.contains("firefox")) {
                browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
            } else if (lowercaseBroswer.contains("rv")) {
                browser = "IE";
            } else if (lowercaseBroswer.contains("postman")) {
                browser = "Postman";
            } else {
                browser = "UnKnown";
            }
            return browser;
        } catch (Exception e) {
            return null;
        }
    }

}
