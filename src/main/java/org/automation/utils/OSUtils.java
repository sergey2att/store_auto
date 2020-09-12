package org.automation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSUtils {

    private OSUtils() {}

    public static OSType getOS() {
       String platformName = System.getProperty("os.name").toLowerCase();
       OSType detectedOS = OSType.OTHER;
        if ((platformName.contains("mac")) || (platformName.contains("darwin"))) {
            detectedOS = OSType.MACOS;
        } else if (platformName.contains("win")) {
            detectedOS = OSType.WINDOWS;
        } else if (platformName.contains("nux")) {
            detectedOS = OSType.LINUX;
        }
        return detectedOS;
    }

    public static int getChromeBrowserVersion() throws IOException {
        StringBuilder lines = new StringBuilder();
        String line;
        String result = "";
        ProcessBuilder processBuilder;
        OSType osType = getOS();

        if (osType == OSType.MACOS) {
            processBuilder = new ProcessBuilder("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome", "--version");
        }
        else if (osType == OSType.LINUX) {
            processBuilder = new ProcessBuilder("google-chrome", "--version");
        } else {
            throw new UnsupportedOperationException("Unable to get chrome version for current OS - " + getOS().name());
        }
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = in.readLine()) != null) {
            lines.append(line);
        }
        in.close();
        Pattern pattern = Pattern.compile("\\s(\\d{2}).");
        Matcher matcher = pattern.matcher(lines);
        if (matcher.find()) {
            result = matcher.group(matcher.groupCount());
        } else {
            throw new RuntimeException("Something went wrong with browser version detection. Browser version: " + lines);
        }
        return Integer.parseInt(result);
    }
}
