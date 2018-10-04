#!/usr/bin/env groovy
package com.towens

def checkoutWithRetries(Integer retryCount = 10) {
    while (retryCount>0) {
        try {
            if (retryCount == 10) {
                throw new Exception('intentional test condition reached')
            }
            checkout scm
            retryCount = 0
        }
        catch (Exception e) {
            msg = e.getMessage()
            if ((retryCount % 5) == 0) {
                echo "Checkout scm failed due to ${msg}.  Retrying"
            }
            retryCount--
            sleep(60)
        }
    }
}
