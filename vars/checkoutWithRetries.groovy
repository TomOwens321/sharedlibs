def call(Integer retryCount = 3) {
    if (binding.hasVariable('busy')) {
        echo 'busy found '
        busy = true
    } else {
        def busy = true
        echo 'created busy'
    }
    while (retryCount>0) {
        try {
            if (retryCount == 3) {
                throw new Exception('Weeeee')
            }
            checkout scm
            retryCount = 0
        }
        catch (Exception e) {
            msg = e.getMessage()
            if ((retryCount % 3) == 0) {
                echo "Checkout scm failed due to ${msg}.  Retrying"
            }
            retryCount--
        }
    }
    busy = false
}
