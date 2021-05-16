from flask import request
import socket
import json
from test_json .flask_test import app


#     val lecture:String,
#     val professor:String,
#     val number:String,
#     val uni:String,
#     val major:String,
#     val classification:String,
#     val grade:String,
#     val credit:String,
#     val teaching_method:String,
#     val time:String){}


def get_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        # doesn't even have to be reachable
        s.connect(('10.255.255.255', 1))
        IP = s.getsockname()[0]
    except:
        IP = '127.0.0.1'
    finally:
        s.close()
    return IP



dict1= {"address":get_ip()}

json_addr = json.dumps(dict1)




if __name__ == "__main__":
    app.run(host=str(get_ip()), port=5000, debug=True)
