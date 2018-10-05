#!/usr/bin/env groovy

def call(Integer retryCount = 10) {
    while (retryCount > 0) {
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
                // echo "Checkout scm failed due to ${msg}.  Retrying"
                sayHello("Checkout scm failed due to ${msg}.  Retrying")
            }
            retryCount--
            sleep(60)
        }
    }
}
