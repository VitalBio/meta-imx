DESCRIPTION = "Parts of ONNX Runtime that needs to be compiled for native system"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=37b5762e07f0af8c74ce80a8bda4266b"

DEPENDS = "libpng zlib"

# For ONNX Runtime v0.3.0 we need only 'protoc' to be compiled for native system
GIT_protobuf = "git://github.com/google/protobuf.git;branch=3.11.x;name=protobuf"
SRCREV_protobuf = "df2bce345d4bc8cdc3eba2a866e11e79e1fff4df"

SRC_URI = "\
    ${GIT_protobuf} \
"

SRCREV_FORMAT = "protobuf"

S = "${WORKDIR}/git"

inherit cmake
inherit native

OECMAKE_SOURCEPATH = "${S}/cmake"

EXTRA_OECMAKE += "\
    -Dprotobuf_BUILD_TESTS=OFF \
    -Dprotobuf_BUILD_EXAMPLES=OFF \
    -Dprotobuf_BUILD_SHARED_LIBS=OFF \
"

do_install() {
    install -d ${D}/${bindir}/${PN}
    install -m 0755 ${B}/protoc ${D}/${bindir}/${PN}
}
