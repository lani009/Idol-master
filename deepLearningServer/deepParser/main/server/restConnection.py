from socket import *

class RESTConnection:
    def __init__(self):
        self.PORT = 7575
        self.HOST = ''
        self.ADDR = (self.PORT, self.HOST)
        self.serverSocket = socket(AF_INET, SOCK_STREAM)
        self.serverSocket.bind(self.ADDR)
        self.serverSocket.listen(100)
        self.clientSocekt, addr_info = serverSocket.accept()
        print('accept')
        print('--client information--')
        print(self.clientSocekt)


    def recv(self):
        return self.serverSocket.recv(65535).decode()