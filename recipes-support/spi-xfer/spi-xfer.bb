DESCRIPTION = "spidev device access"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/intel-aero/sample-apps.git;protocol=https"
SRCREV = "16054d3baa6292361436f0cadec3733ef655ee7e"
S = "${WORKDIR}/git"

SRC_FILE = "${WORKDIR}/git/spidev-app/spi_xfer.c"

do_compile() {
    ${CC} spidev-app/spi_xfer.c ${LDFLAGS} -o spi_xfer
}

do_install() {
    install -d ${D}${bindir}/
    install spi_xfer ${D}${bindir}/
}
