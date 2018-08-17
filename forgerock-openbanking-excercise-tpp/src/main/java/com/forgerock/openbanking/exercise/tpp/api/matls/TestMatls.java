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
package com.forgerock.openbanking.exercise.tpp.api.matls;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "Test your matls setup", description = "test the installation of transport certificate in your application")
@RequestMapping("/api/test/matls")
public interface TestMatls {

    @ApiOperation(value = "Call the jwkms matls test endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return the jwkms response", response = String.class)
        })
    @RequestMapping(value = "/jwkms", method = RequestMethod.GET)
    ResponseEntity<String> testMatlsForJwkMs();

    @ApiOperation(value = "Call the directory matls test endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return the directory response", response = String.class)
    })
    @RequestMapping(value = "/directory", method = RequestMethod.GET)
    ResponseEntity<String> testMatlsForDirectory();

}
