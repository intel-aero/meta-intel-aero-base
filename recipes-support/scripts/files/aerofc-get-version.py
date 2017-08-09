#! /usr/bin/env python

import os,sys
from pymavlink import mavutil

if len(sys.argv)!=2:
    print "Provide ipaddress:port like below,"
    print "aerofc-get-version.py 127.0.0.1:5760"
    sys.exit(0)

mav_con = mavutil.mavlink_connection('tcp:'+sys.argv[1],planner_format=False, notimestamps=True, robust_parsing=True)

recv_data = mav_con.recv_match(type='HEARTBEAT',blocking = True)

msg = mav_con.mav.command_long_encode(0,0,520,0,1,0,0,0,0,0,0)
mav_con.write(msg.pack(mav_con.mav))

while True:
    msg=mav_con.recv_msg()
    if msg is not None:
        if msg.get_type() is "AUTOPILOT_VERSION":
            autopilot_version= msg.flight_sw_version
            break
majorVersion=(autopilot_version>>(8*3))&0xFF;
minorVersion=(autopilot_version>>(8*2))&0xFF;
patchVersion=(autopilot_version>>(8*1))&0xFF;

fw_version=str(majorVersion)+"."+str(minorVersion)+"."+str(patchVersion)
print "FW version: "+fw_version
