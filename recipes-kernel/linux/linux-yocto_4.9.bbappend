# Intel Aero Machine kernel support
KBRANCH_intel-aero ?= "standard/base"
SRCREV_machine_intel-aero ?= "2c31d7a45ae75159a7d991abdeb7002a4493af7f"
LINUX_VERSION_intel-aero ?= "4.9.27"
COMPATIBLE_MACHINE_intel-aero = "intel-aero"

KERNEL_EXTRA_FEATURES = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# module is not enabled, but brought in by meta-virtualization
KERNEL_MODULE_AUTOLOAD_remove = "kvm-amd"

# List of configs to enable in kernel .config
SRC_URI += "file://defconfig"

# Don't run config check as we're using defconfig
do_kernel_configcheck() {
}

# List of binary files
SRC_URI += "file://shisp_2401a0_v21.bin"

# List of patches to apply
SRC_URI += "file://0001-thermal-add-cherryview-support-to-soc-dts.patch"
SRC_URI += "file://0002-usb-otg-add-cherryview-support.patch"
SRC_URI += "file://0003-FIXUP-usb-otg-add-cherryview-support.patch"
SRC_URI += "file://0004-REMOVEME-Revert-mfd-intel_soc_pmic_bxtwc-Fix-usbc-in.patch"
SRC_URI += "file://0005-REMOVEME-Revert-mfd-intel_soc_pmic_bxtwc-Add-bxt_wco.patch"
SRC_URI += "file://0006-pmic-intel-port-whiskey-cove-driver.patch"
SRC_URI += "file://0007-pmic-Update-WC-pmic-to-new-API.patch"
SRC_URI += "file://0008-regulator-whiskey_cove-implements-WhiskeyCove-pmic-V.patch"
SRC_URI += "file://0009-regulator-whiskey_cove-fixup-to-build-with-gcc-6.patch"
SRC_URI += "file://0010-pmic-whiskeycove-add-vqmmc-regulator-for-SD-host-vol.patch"
SRC_URI += "file://0011-intel-mid-split-keyboard-gpio-SFI-implementation-fro.patch"
SRC_URI += "file://0012-input-soc_button_array-add-debounce-parameter-to-the.patch"
SRC_URI += "file://0013-acpi-Workaround-for-not-registering-CAN-controller.patch"
SRC_URI += "file://0014-Temporarily-remove-BXT-PMIC-driver.patch"
SRC_URI += "file://0015-aufs4-synchronize-source-with-aufs4-standalone.patch"
SRC_URI += "file://0016-fixup-usb-otg-add-cherryview-support.patch"

do_install_append() {
	install -d ${D}/lib/firmware
	install -m 0777 ${WORKDIR}/shisp_2401a0_v21.bin ${D}/lib/firmware
}
