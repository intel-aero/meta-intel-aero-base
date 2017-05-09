FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "file://init-install-efi-nouserinteraction.sh"

do_install() {
	install -m 0755 ${WORKDIR}/init-install-efi-nouserinteraction.sh ${D}/install-efi.sh
}
