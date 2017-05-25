DESCRIPTION = "Add build_version firstboot service file"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=93888867ace35ffec2c845ea90b2e16b"


FILESEXTRAPATHS_prepend := "${THISDIR}/initscripts:"
inherit systemd
SRC_URI += "file://build_version.sh \
	    file://firstboot-build_version.service \
	   "
PACKAGECONFIG[systemd] = "--with-systemdunitdir=${systemd_unitdir}/system/"
SYSTEMD_SERVICE_${PN} += "firstboot-build_version.service"
FILES_${PN} += "${sysconfdir}/init.d/*"

do_install_append () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/build_version.sh ${D}${sysconfdir}/init.d
	install -d ${D}${systemd_unitdir}/system
	install -D -m 0644 ${WORKDIR}/firstboot-build_version.service ${D}${systemd_unitdir}/system
	
}
