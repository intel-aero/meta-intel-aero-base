FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SUMMARY = "SysV init scripts for Intel Aero"
DESCRIPTION = "SysV init scripts for Intel Aero"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
PR = "r1"

INHIBIT_DEFAULT_DEPS = "1"

S = "${WORKDIR}"

#inherit update-alternatives
#DEPENDS_append = " update-rc.d-native"
#PACKAGE_WRITE_DEPS_append = " ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd-systemctl-native','',d)}"

SRC_URI += "file://LICENSE \
            file://ds4.sh \
	    file://aero-wd.sh \
	   "

do_install () {
#
# Create directories and install device independent scripts
#
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc1.d
	install -d ${D}${sysconfdir}/rc2.d
	install -d ${D}${sysconfdir}/rc3.d
	install -d ${D}${sysconfdir}/rc4.d
	install -d ${D}${sysconfdir}/rc5.d
	install -d ${D}${sysconfdir}/rc6.d

	install -m 0755 ${WORKDIR}/ds4.sh ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/aero-wd.sh ${D}${sysconfdir}/init.d

	ln -sf ../init.d/ds4.sh ${D}${sysconfdir}/rc1.d/S10ds4
	ln -sf ../init.d/ds4.sh ${D}${sysconfdir}/rc2.d/S10ds4
	ln -sf ../init.d/ds4.sh ${D}${sysconfdir}/rc3.d/S10ds4
	ln -sf ../init.d/ds4.sh ${D}${sysconfdir}/rc4.d/S10ds4
	ln -sf ../init.d/ds4.sh ${D}${sysconfdir}/rc5.d/S10ds4

	ln -sf ../init.d/aero-wd.sh ${D}${sysconfdir}/rc1.d/S10aero-wd
	ln -sf ../init.d/aero-wd.sh ${D}${sysconfdir}/rc2.d/S10aero-wd
	ln -sf ../init.d/aero-wd.sh ${D}${sysconfdir}/rc3.d/S10aero-wd
	ln -sf ../init.d/aero-wd.sh ${D}${sysconfdir}/rc4.d/S10aero-wd
	ln -sf ../init.d/aero-wd.sh ${D}${sysconfdir}/rc5.d/S10aero-wd
}
