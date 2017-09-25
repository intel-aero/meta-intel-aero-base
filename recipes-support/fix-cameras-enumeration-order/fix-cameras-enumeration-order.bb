SUMMARY = "Fix cameras enumeration order"
DESCRIPTION = "RealSense is being power on in BIOS(changed in BIOS \
Aero-01.00.13) causing it sometimes being loaded before atomisp cameras(ov8588 \
and ov7251), this will make the RealSense module only be loaded after atomisp \
module"
LICENSE = "GPLv2"
LICENSE_PATH = "${S}"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PR = "r1"

SRC_URI = "file://realsense.conf"

do_install() {
    install -d ${D}${sysconfdir}/modprobe.d/
    install -m 0744 ${WORKDIR}/realsense.conf ${D}${sysconfdir}/modprobe.d/
}