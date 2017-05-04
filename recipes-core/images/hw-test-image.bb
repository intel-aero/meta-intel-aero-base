LICENSE = "MIT"
IMAGE_FEATURES += "splash "
inherit core-image

CORE_IMAGE_EXTRA_INSTALL += "rpm"
CORE_IMAGE_EXTRA_INSTALL += "connman connman-client"

IMAGE_FEATURES += "ssh-server-openssh"
CORE_IMAGE_EXTRA_INSTALL += "openssh-sftp-server"
