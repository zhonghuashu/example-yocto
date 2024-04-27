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
$ git clone -b kirkstone --depth=1 https://git.yoctoproject.org/poky

$ cd src/example_app
$ source ~/github/yocto/poky/oe-init-build-env build
# Fix advisories: Do not use Bitbake as root.
$ touch conf/sanity.conf
```

## bb_hello_print
- bb_hello_print: [Example](https://docs.yoctoproject.org/bitbake/2.6/bitbake-user-manual/bitbake-user-manual-hello.html) from BitBake manual
```shell
# Setting Up the BitBake Environment.
$ cd ~/github/yocto/example-yocto/src/bb_hello_print
$ export PATH=/root/github/yocto/poky/bitbake/bin:$PATH
$ locale-gen en_US.UTF-8
$ bitbake --version
BitBake Build Tool Core version 2.0.0
# BitBake uses that directory to find the metadata it needs for your project.
$ export BBPATH="/root/github/yocto/example-yocto/src/bb_hello_print"
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
$ export PATH=/root/github/yocto/poky/bitbake/bin:$PATH
$ locale-gen en_US.UTF-8
$ bitbake nano
```

## example_app
-  
```shell
$ cd test/hello
# Move generated hello.tar.gz file to recipes-apps/hello
$ tar -czvf hello.tar.gz *
```
