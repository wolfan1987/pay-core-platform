package org.andrew.commons.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IP处理工具类。
 *
 * @author AndrewLiu (liudaan@chinaexpresscard.com)  Created in 2018/2/1 10:36
 */
public class IpUtils {

    private static String currentMachIp = null;

    /**
     * 获取本地IP。
     *
     * @return 地址
     * @throws Exception 异常
     */
    public static InetAddress getLocalHostLanAddress() throws Exception {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ni = NetworkInterface.getNetworkInterfaces(); ni.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ni.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration ias = iface.getInetAddresses(); ias.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) ias.nextElement();
                    // 排除loopback类型地址
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            return InetAddress.getLocalHost();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取IP地址。
     *
     * @return ip地址字符串
     */
    public static String getHostIp() {
        if (currentMachIp == null) {
            try {
                InetAddress ip = getLocalHostLanAddress();
                currentMachIp = ip.getHostAddress();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return currentMachIp;
    }
}
