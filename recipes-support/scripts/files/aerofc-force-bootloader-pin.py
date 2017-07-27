#!/usr/bin/python
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, see
# http://www.gnu.org/licenses/old-licenses/gpl-2.0.html.
"""
Copyright (C) 2017  Intel Corporation. All rights reserved.
"""

import commands
import sys

def read_pin_value():
	print "Reading force bootloader pin..."
	status, output = commands.getstatusoutput("/usr/bin/spi_xfer -b 1 -c 1 -d 0001 -w 2")
	if status != 0:
		print "Unable to read force bootloader pin value"
		return

	lines = output.split('\n')
	words = lines[4].split()

	print "Force bootloader pin value =",
	print words[2]

def set_pin_value(value):
	print "Writing force bootloader pin..."
	if value:
		status, output = commands.getstatusoutput("/usr/bin/spi_xfer -b 1 -c 1 -d 0181 -w 2")
	else:
		status, output = commands.getstatusoutput("/usr/bin/spi_xfer -b 1 -c 1 -d 0081 -w 2")

	if status != 0:
		print "Unable to set force bootloader pin value"
		return
	
	print "Done!"

if len(sys.argv) < 2:
	read_pin_value()
	print "Help:"
	print sys.argv[0],
	print "<1 or 0> to set the force bootloader pin value"
else:
	value = sys.argv[1]

	if value == '0':
		set_pin_value(False)
	else:
		set_pin_value(True)

	read_pin_value()
