# Introduction
Customize embedded linux using Yocto build.


## Setup host package
```shell
# For ubuntu-22.04
$ apt update
$ apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev python3-subunit mesa-common-dev zstd liblz4-tool file locales libacl1
```

## Setup build environment
```shell
$ mkdir ~/yocto && cd yocto
# Release 4.0 (kirkstone)
$ git clone -b kirkstone --depth=1 https://git.yoctoproject.org/poky
# Define Yocto Project’s build environment on your build host.
$ source poky/oe-init-build-env
# Build image with minimal size.
$ bitbake core-image-minimal
# Simulate Your Image Using QEMU.
# runqemu qemux86-64. Error: yocto Could not initialize SDL(x11 not available)
$ runqemu nographic
Poky (Yocto Project Reference Distro) 4.0.18 qemux86-64 /dev/ttyS0
qemux86-64 login:

```

## bb_hello_print
- bb_hello_print: [Example](https://docs.yoctoproject.org/bitbake/2.6/bitbake-user-manual/bitbake-user-manual-hello.html) from BitBake manual
```shell
#
# Run without oe-init-build-env script.
#
# Setting Up the BitBake Environment.
$ cd ~/yocto/example-yocto/bb_hello_print
$ export PATH=/home/shu/yocto/poky/bitbake/bin:$PATH
$ sudo locale-gen en_US.UTF-8
$ bitbake --version
BitBake Build Tool Core version 2.0.0
# BitBake uses that directory to find the metadata it needs for your project.
$ export BBPATH="/home/shu/yocto/example-yocto/bb_hello_print"
# Run BitBake With a Target.
$ bitbake printhello


********************
*                  *
*  Hello, World!   *
*                  *
********************
```

## bb_hello_editor
- bb_hello_editor: Example builds the nano text editor from source.
```shell
$ cd bb_hello_editor
$ source ~/yocto/poky/oe-init-build-env build
# Fix advisories: Do not use Bitbake as root.
# touch conf/sanity.conf
# Add customer layers into build/conf/bblayers.conf: BBLAYERS
# ?= /root/github/yocto/example-yocto/bb_hello_editor/meta-hello 

$ bitbake nano

```

## example_app
-  
```shell
$ cd example-yocto/example_app/meta-mylayer/recipes-apps/hello/src
# Move generated hello.tar.gz file to recipes-apps/hello
$ tar -czvf hello.tar.gz *
```

## meta-mylayer
- Create customer layer.
```shell
# Create customer layer.
$ cd ~/yocto
$ source poky/oe-init-build-env
$ cd ~/yocto/example-yocto
$ bitbake-layers create-layer meta-mylayer
# Add customer layer into build/conf/bbblayers.conf: BBLAYERS ?= /home/shu/yocto/example-yocto/meta-mylayer
# Build example recipe.
$ cd ~/yocto/build
$ bitbake example
NOTE: Executing Tasks
***********************************************
*                                             *
*  Example recipe created by bitbake-layers   *
*                                             *
***********************************************
```
- 