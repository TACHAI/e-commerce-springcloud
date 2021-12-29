package com.laishishui.ecommerce.service;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * <h1> RSA 非对称加密算法： 生成公钥和私钥</h1>
 * Create by tachai on 2021/12/29 9:04 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RSATest {

    @Test
    public void generateKeyBytes()throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        //生成公钥和私钥对
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        log.info("private key: [{}]", Base64.encode(privateKey.getEncoded()));
        log.info("public key: [{}]", Base64.encode(publicKey.getEncoded()));
        // private key: [MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDJL+3/Yco5Kn8NKGmDKSpHF12/Hlh0XWt/29tj7PRom9iCYfAokjjNcnrzU676o254N9dnh7WgoHo0EGlqFGCBLem85cWdss2eUxD7PKzonIhnigMXg7cILYwmMKiaPTCr9i3iC4Ca9/5ExtgTRRMFxiV7M1SzqPvX4FKDKUbSdggfzzyznkUgHFi8DzCxF96pHZp9sPfDrCt5fiVPNaI67HejLvclYg0XS0gy5W6x9gP173pwPJyOZGg8Uh7bR/uuJG4ooMo3QMniJ00s7cmjM+Q05xpvkifP1x56PtBWIb3C6WvvudU277ToCqEFwqiMaGn0/M9zzuNDohf7/n5ZAgMBAAECggEAOko0voVqvd1eKkXHLEVFBovykWkNX8mhV84vZmhE9/oPZNdTaa3T31KH5PAeBmhLnXEwa8KaEQvt3B/VKvL0cvkGe8Bm3SXtBS40AVa3Ej4oFzeZZbq30RrBPF9kuit4appM+O00ObIYbXzfJdYhiM68FVHt/I+9R/KqdW4EIv2kHIQnivPtzQJAIsw2PJJEaFrjvFA4IIo8yFAHPiSdM1hMvqgozHPJKfykjNJ2hkUrNtjlmHqjAq7hKmBPvEiqYPLDCYTePCFQ4it9J4Vd5FGJeIFuVmCB9cOGrrCFwnsxq5AgunBSyzXo+CuuMc07acGZTg27dlXPUEGa87IKAQKBgQDne0/R95wKqSfkXMXLDPWCgIsDFCHfvYtColoXDxxtTlGmtR0IU/QUySA2ylnfPIcm/1gQSRdpW7TWc8pWhw0UgXpa4mKuIMWmVGVBRm6lppnA5zCuvUDXTqPolQ80E8fgOa8z/KXIl0MEv9I4TgIXQLVFF6vzS/O5xs9+LD71MQKBgQDefyznsxBLK4IAkq0Y8Z+D+fzUeHGkjVmf8r3QCoyRvHrjmnfdm0b1J1CtFMDMAsjZrGJJ8Fm7yCalc02QnNAWjl4ymm0/LpfeiCT2BMVIa3zGycN8r8xt60/XOGipNyzHY1hcXuv2rlVty/B9ddvkoRv+hpyAbAFEemIrgnRxqQKBgQDPlIMl1Rf3b224mYE97ycSmlcOsbUWwRDq9lA3sXNaigsqpeudFjwhj6L1v0wMpmjEjeLY59K4xmZKkZd3NLCEHQAuFClN4VGdML2EZba/kcD7fdboPAqziW/vGAyENODesJvu0LeSRCLzVVkc6MdzNrHXfbzS9t9fT2uux9rFYQKBgQC5+huS4H3yFz/nMrM1cmIfcsn+314+aVhM9h3q06aD+sPNk8Q1nCFHscEhq4MsA/fW2tydaSXYbIjBKwveoPTepcnfCm3erheLjsk2Su6MhiwaDXQ1uMxr5JEsGJ6DMvdfAo9NRpp962LFD/7JH8bZ6ED6E5KlVaIJlzKPo8+pYQKBgQCHNPyGadXINJexCtDxHlUsxZ8PLOtLs4GWuKMzpBZIPhiJE2YHro2mn4DJs1oo3EdzB+RfEm4gUahu3ZX3BGIxbYMMvA5Yp/fw5eZpLbjNTb9USfFQmMsOFwaqq9F/Rd3Q3EBNzH+SbiE58fGpVQ0xPbd5sky2wjPi5qjK4aUTfQ==]

        // public key: [MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyS/t/2HKOSp/DShpgykqRxddvx5YdF1rf9vbY+z0aJvYgmHwKJI4zXJ681Ou+qNueDfXZ4e1oKB6NBBpahRggS3pvOXFnbLNnlMQ+zys6JyIZ4oDF4O3CC2MJjComj0wq/Yt4guAmvf+RMbYE0UTBcYlezNUs6j71+BSgylG0nYIH888s55FIBxYvA8wsRfeqR2afbD3w6wreX4lTzWiOux3oy73JWINF0tIMuVusfYD9e96cDycjmRoPFIe20f7riRuKKDKN0DJ4idNLO3JozPkNOcab5Inz9ceej7QViG9wulr77nVNu+06AqhBcKojGhp9PzPc87jQ6IX+/5+WQIDAQAB]

    }
}
