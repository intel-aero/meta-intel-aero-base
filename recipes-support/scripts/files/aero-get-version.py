#!/usr/bin/env python

##############################################################################
# This script gets current versions of various software/firmware on Aero board.
# It reads current BIOS, OS, FPGA, and Airmap versions and prints on console.
#
# Author: Mandeep Bansal <mandeep.bansal@intel.com>
#      Pranav Tipnis <pranav.tipnis@intel.com>
#
############################################################################

import commands
import os.path
import subprocess

def main():
    print "\n"
    BIOS_version()
    OS_version()
    AirMap_version()
    FPGA_version()
    AeroFC_version()
    print "\n"

def AeroFC_version():
    try:
        status, output = commands.getstatusoutput("aerofc-get-version.py")
        if status != 0:
            print "AeroFC firmware version = unknown"
        else:
            print output
    except:
        print "AeroFC firmware version = unknown"

def FPGA_version():
    print "FPGA_VERSION =",
    try:
        # There is a bug in aero_sample and aero-rtf(now fixed) that was
        # causing a tranmission delay of 1 byte, that is why was
        # necessary to read it 2 times to get the right value and the
        # version was on the wrong byte.
        # So it will continue reading 2 times and comparing the values read
        # to decide what to show.
        status1, output1 = commands.getstatusoutput("/usr/bin/spi_xfer -b 1 -c 1 -d 00 -w 2")
        status2, output2 = commands.getstatusoutput("/usr/bin/spi_xfer -b 1 -c 1 -d 00 -w 2")
        if status1 != 0 or status2 != 0:
            print "unknown"
        else:
            lines = output1.split('\n')
            words = lines[4].split()
            v1 = words[2]

            lines = output2.split('\n')
            words = lines[4].split()
            v2 = words[2]

            if (v2 == v1 and v1 != '0x0'):
                print v1
            else:
                print words[1]
    except:
        print "unknown"

def AirMap_version():
    print "AIRMAP_VERSION =",
    try:
        status, output = commands.getstatusoutput("python /etc/airmap/AirMapSDK-Embedded/getver.py")
        if status != 0:
            print "unknown"
        else:
            print output
    except:
        print "unknown"

def OS_version(rootdir='/'):
    version = None
    try:
        with open(os.path.join(rootdir, 'etc', 'os-release'), 'r') as f:
            for line in f:
                if line.startswith('PRETTY_NAME='):
                    version = line.split('PRETTY_NAME=')[1].strip('"').strip()
                    if version:
                        break
    except:
        pass

    if not version:
        version = 'unknown'

    print "OS_VERSION = %s" % version

def BIOS_version():
    try:
        line = subprocess.check_output("dmidecode | grep Aero-", shell=True)
        print "BIOS_VERSION = " + (line.split(':')[1]).strip()
    except:
        print "BIOS_VERSION = unknown"


if __name__ == "__main__":
    main()
