package utils;

/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.    
 */

//package org.opentides.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class StringUtil {
	/**
	 * Encrypt password by using SHA-256 algorithm, encryptedPassword length is
	 * 32 bits
	 * 
	 * @param clearTextPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 *             reference
	 *             http://java.sun.com/j2se/1.4.2/docs/api/java/security
	 *             /MessageDigest.html
	 */
	public static String getEncryptedPassword(byte[] salt,
			String clearTextPassword) {
		try {
			String hexSalt = HexBin.encode(salt);
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((hexSalt + clearTextPassword).getBytes());
			return new sun.misc.BASE64Encoder().encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			// _log.error("Failed to encrypt password.", e);
		}
		return "";
	}

	public static Boolean isEmailAddress(String s) {
		return Pattern
				.compile(
						"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
				.matcher(s).matches();
	}

}
