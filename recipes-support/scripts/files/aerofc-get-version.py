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
# http://www.gnu.org/licenses/old-licenses/gpl-2.0.html

from __future__ import print_function
from pymavlink import mavutil
import time
import sys

tcp_ip_port = "127.0.0.1:5760"
target_sys_id = 1
target_comp_id = mavutil.mavlink.MAV_COMP_ID_ALL
program_timeout = time.time() + 3
autopilot_version = 0

if len(sys.argv) > 1:
	tcp_ip_port = sys.argv[1]

mav_con = mavutil.mavlink_connection('tcp:'+tcp_ip_port)
request_msg = mav_con.mav.command_long_encode(target_sys_id, target_comp_id,
	mavutil.mavlink.MAV_CMD_REQUEST_AUTOPILOT_CAPABILITIES,
	0, # confirmation
	1, # param1(request version)
	0, # param2
	0, # param3
	0, # param4
	0, # param5
	0, # param6
	0) # param7
version_request_timeout = 0

while True:
	msg = mav_con.recv_match(blocking=True, timeout=1)

	if msg is not None and msg.get_type() is "AUTOPILOT_VERSION":
		autopilot_version = msg.flight_sw_version
		break

	if time.time() > program_timeout:
		break

	if time.time() > version_request_timeout:
		version_request_timeout = time.time() + 1
		mav_con.write(request_msg.pack(mav_con.mav))

if autopilot_version == 0:
	print("AeroFC firmware version = unknown")
	exit()

majorVersion = (autopilot_version >> 24) & 0xFF
minorVersion = (autopilot_version >> 16) & 0xFF
patchVersion = (autopilot_version >> 8) & 0xFF

fw_version = str(majorVersion) + "." + str(minorVersion) + "." + str(patchVersion)
print("AeroFC firmware version = " + fw_version)

