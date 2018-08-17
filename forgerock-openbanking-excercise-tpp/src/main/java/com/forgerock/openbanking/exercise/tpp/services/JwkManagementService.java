/*
 * The contents of this file are subject to the terms of the Common Development and
 *  Distribution License (the License). You may not use this file except in compliance with the
 *  License.
 *
 *  You can obtain a copy of the License at https://forgerock.org/license/CDDLv1.0.html. See the License for the
 *  specific language governing permission and limitations under the License.
 *
 *  When distributing Covered Software, include this CDDL Header Notice in each file and include
 *  the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 *  Header, with the fields enclosed by brackets [] replaced by your own identifying
 *  information: "Portions copyright [year] [name of copyright owner]".
 *
 *  Copyright 2018 ForgeRock AS.
 *
 */
package com.forgerock.openbanking.exercise.tpp.services;

import com.forgerock.openbanking.exercise.tpp.configuration.TppConfiguration;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.UUID;

@Service
public class JwkManagementService {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwkManagementService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TppConfiguration tppConfiguration;

    public String signJwt(String issuerId, JWTClaimsSet jwtClaimsSet) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        if (issuerId != null) {
            headers.add("issuerId", issuerId);
        }
        HttpEntity<String> request = new HttpEntity<>(jwtClaimsSet.toString(), headers);
        LOGGER.debug("Sign claims {}", jwtClaimsSet);
        return restTemplate.postForObject(tppConfiguration.getJwkms() + "/api/crypto/signClaims", request, String.class);
    }

    public String testmatls() {
        return restTemplate.getForEntity(tppConfiguration.getJwkms() + "/mtls/test", String.class).getBody();
    }
}
