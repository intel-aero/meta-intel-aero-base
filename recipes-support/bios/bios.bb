DESCRIPTION = "BIOS for Intel Aero compute board"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

SRC_URI += "file://BIOSUPDATE.fv\
	file://Proprietary\
	"

do_install_append() {
	install -d ${D}/boot/
	install -m 0755 ${WORKDIR}/BIOSUPDATE.fv ${D}/boot/
}

FILES_${PN} += "/boot/BIOSUPDATE.fv"
