DESCRIPTION = "LED Notification for Init"
LICENSE = "GPLv2"
LICENSE_PATH = "${S}"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit systemd
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://init_led_complete.service \
		file://init_complete.sh \
		file://init_led_start.service \
		file://init_start.sh"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--enable-systemd --with-systemdsystemunitdir=${systemd_unitdir}/system/,--disable-systemd"

SYSTEMD_SERVICE_${PN} += "init_led_complete.service init_led_start.service"
SYSTEMD_AUTO_ENABLE="enable"

FILES_${PN} += "${sbindir}/*"

do_install_append() {
	install -d ${D}${sbindir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0755 ${WORKDIR}/init_complete.sh ${D}${sbindir}
	install -m 0755 ${WORKDIR}/init_start.sh ${D}${sbindir}

	install -D -m 0644 ${WORKDIR}/init_led_complete.service ${D}${systemd_unitdir}/system
	install -D -m 0644 ${WORKDIR}/init_led_start.service ${D}${systemd_unitdir}/system
}
