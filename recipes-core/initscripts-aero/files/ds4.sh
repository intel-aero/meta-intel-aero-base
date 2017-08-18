#!/bin/sh
# DS4 power was turned on in BIOS 01.00.13 but this is causing DS4 not
# be enumerated after a reboot, so here it is turned off and then on
# fixing the enumeration problem.

echo 405 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio405/direction
echo 0 > /sys/class/gpio/gpio405/value
sleep 1
echo 1 > /sys/class/gpio/gpio405/value
