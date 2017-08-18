DESCRIPTION = "Scripts that should run in Intel Aero after boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
PR = "r1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://LICENSE"
SRC_URI += "file://ds4.sh"
SRC_URI += "file://ds4.service"
SRC_URI += "file://aero-wd.service"

inherit systemd

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--enable-systemd --with-systemdsystemunitdir=${systemd_unitdir}/system/,--disable-systemd"

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/ds4.sh ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -D -m 0644 ${WORKDIR}/ds4.service ${D}${systemd_unitdir}/system
    install -D -m 0644 ${WORKDIR}/aero-wd.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} += "ds4.service"
SYSTEMD_SERVICE_${PN} += "aero-wd.service"
