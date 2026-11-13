package org.baeldung.service;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.baeldung.persistence.dao.AdresseMacRepository;
import org.baeldung.persistence.model.AdresseMac;
import org.baeldung.persistence.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.exception.GeoIp2Exception;

@Component
public class AdresseMacService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private AdresseMacRepository adresseMacRepository;

	public AdresseMacService(AdresseMacRepository adresseMacRepository) {
		this.adresseMacRepository = adresseMacRepository;
	}

	public void verifyDevice(User user, HttpServletRequest request) throws IOException, GeoIp2Exception {

        String ip = extractIp(request);
        System.out.println("Adresse Ip: "+ip);
        String mac = getMACAddress(ip);
        System.out.println("Adresse mac: "+mac);
        //String location = getIpLocation(ip);
        AdresseMac existingMac = findExistingMac(user.getId(), mac);

        if (Objects.isNull(existingMac)) {
            AdresseMac adresseMac = new AdresseMac();
            adresseMac.setUserId(user.getId());
            adresseMac.setSupprime(0);
            adresseMac.setMac(mac);
            adresseMac.setLastLoggedIn(new Date());
            adresseMacRepository.save(adresseMac);
        } else {
        	existingMac.setLastLoggedIn(new Date());
        	adresseMacRepository.save(existingMac);
        }

    }

	 private AdresseMac findExistingMac(Long userId, String mac) {
	        List<AdresseMac> knownDevices = adresseMacRepository.findByUserId(userId);
	        for (AdresseMac existingMac : knownDevices) {
	            if (existingMac.getMac().equals(mac)) {
	                return existingMac;
	            }
	        }

	        return null;
	    }

	private String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request.getHeader("x-forwarded-for");
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
           // clientIp = request.getLocalAddr();
        }

        return clientIp;
    }
	private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

	private static String getMACAddress(String ip) {
        String macAddress = "";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        try {
            if (LOOPBACK_ADDRESS.equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
                InetAddress inetAddress = InetAddress.getLocalHost();
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
                byte[] mac = networkInterface.getHardwareAddress();
                if (networkInterface == null || mac == null) {
                    return "localhost";
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
                macAddress = sb.toString().trim().toUpperCase();
                return macAddress;
            }

            // For non-local IPs, we can't reliably get MAC across network boundaries
            // Return a placeholder or the IP itself as identifier
            return "remote-" + ip.replace(".", "-").replace(":", "-");
        } catch (Exception e) {
            // logger not available in static context
            return "unknown";
        }
    }
}
