DESCRIPTION = "Initialization scripts for Intel Aero"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
PR = "r1"

INHIBIT_DEFAULT_DEPS = "1"

S = "${WORKDIR}"

SRC_URI = "file://LICENSE"
SRC_URI += "file://ds4.sh"
SRC_URI += "file://ds4.service"
SRC_URI += "file://aero-wd.service"

inherit systemd

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/ds4.sh ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -D -m 0644 ${WORKDIR}/ds4.service ${D}${systemd_unitdir}/system
    install -D -m 0644 ${WORKDIR}/aero-wd.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} += "ds4.service"
SYSTEMD_SERVICE_${PN} += "aero-wd.service"
