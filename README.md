# Introduction
Customize embedded linux distribution using Yocto build.


## Setup host package
```shell
# For ubuntu-22.04
$ apt update
$ apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev python3-subunit mesa-common-dev zstd liblz4-tool file locales libacl1
```

## Build Poky Linux
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

## Customize Linux dist 
### Customize own layer
- Refer to [定制Yocto系统](https://zhuanlan.zhihu.com/p/663983810)
```shell
$ source poky/oe-init-build-env

# Create own layer.
$ cd ~/yocto/example-yocto
$ bitbake-layers create-layer meta-costa-embedded

# Assess how compatible your layer is with the Yocto Project
# Note: meta-costa-embedded shall be removed from bblayers.conf.
$ yocto-check-layer /home/shu/yocto/example-yocto/meta-costa-embedded
INFO: Ran 7 tests in 175.368s
INFO: OK
INFO:  (skipped=2)
INFO: 
INFO: Summary of results:
INFO: 
INFO: meta-costa-embedded ... PASS

# Add created layer into Yocto project bblayers.conf.
$ cd ~/yocto/build
$ bitbake-layers add-layer ../example-yocto/meta-costa-embedded
NOTE: Starting bitbake server...
$ bitbake-layers show-layers
NOTE: Starting bitbake server...
layer                 path                                      priority
==========================================================================
meta                  /home/shu/yocto/poky/meta                 5
meta-poky             /home/shu/yocto/poky/meta-poky            5
meta-yocto-bsp        /home/shu/yocto/poky/meta-yocto-bsp       5
meta-costa-embedded     /home/shu/yocto/example-yocto/meta-costa-embedded  6

# Check if example can be run successful (~/yocto/build).
$ bitbake example
$ bitbake -c clean
$ bitbake -c build
***********************************************
*                                             *
*  Example recipe created by bitbake-layers   *
*                                             *
***********************************************
```

### Customize Linux distribution
- Refer to [定制Yocto系统](https://zhuanlan.zhihu.com/p/663983810)
- Customize distribution name in distro/costa-embedded.conf. 
- Change local.conf to DISTRO from "Poky" to "costa-embedded".
```shell
$ source poky/oe-init-build-env
# Yocto start rebuild using new Linux distribution.
$ bitbake example
$ bitbake core-image-minimal

# Start new Linux distribution in QEMU.
$ runqemu nographic
costa-embedded (Costa Embedded Linux by Yocto) 4.0.18 qemux86-64 /dev/ttyS0
qemux86-64 login: root
root@qemux86-64:~#
```
### Append OSS packages
- Refer to [添加包到镜像中](https://zhuanlan.zhihu.com/p/666675477)
```shell
# Append OSS dropbear to recipes-core/images/costa-embedded-image.bb
# Build image include appended package.
$ bitbake costa-embedded-image.bb
# Start Linux distribution with dropbear server started.
$ runqemu nographic
Starting Dropbear SSH server: Generating 2048 bit rsa key
Starting syslogd/klogd: done
costa-embedded (Costa Embedded Linux by Yocto) 4.0.18 qemux86-64 /dev/ttyS0
```

### Append own developed packages
- Refer to [添加包到镜像中](https://zhuanlan.zhihu.com/p/666675477)
- Refer to [devtool Quick Reference](https://docs.yoctoproject.org/ref-manual/devtool-reference.html)
- 
```shell
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

