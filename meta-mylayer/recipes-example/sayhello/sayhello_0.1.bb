SUMMARY = "SayHello demo"
DESCRIPTION = "SayHello project used in Yocto demo"

# NOTE: Set the License according to the LICENSE file of your project
#       and then add LIC_FILES_CHKSUM accordingly
LICENSE = "CLOSED"

# Assuming the branch is main
# Change <username> accordingly
SRC_URI = "file:///home/shu/yocto/example-lib/sayhello"

DEPENDS += "libhello"
RDEPENDS:${PN} += "libhello"

# S = "${WORKDIR}/git"

do_install(){
   install -d ${D}/usr/bin
   install -m 0700 sayhello ${D}/usr/bin
}