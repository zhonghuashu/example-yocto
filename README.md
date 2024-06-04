# Introduction
Customize embedded linux distribution using Yocto build.
- VS Code extension: Yocto Project BitBake

# Setup host package
```shell
# For ubuntu-22.04
$ apt update
$ apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev python3-subunit mesa-common-dev zstd liblz4-tool file locales libacl1
```

# Build Poky Linux
- Refer to [Yocto Quick build](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html)
- Refer to [Yocto构建流程](https://zhuanlan.zhihu.com/p/663983749)
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

# Exit QEMU
$ sudo killall qemu-system-x86_64

```

# Example: bb_hello_print
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
