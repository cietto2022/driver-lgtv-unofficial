"""Basic webOS client example."""
import asyncio
from datetime import datetime
from contextlib import suppress
from aiowebostv import WebOsClient
from java.lang import System
from websockets.exceptions import ConnectionClosed, ConnectionClosedOK
from aiowebostv.exceptions import WebOsTvCommandError

HOST = "192.168.11.92"
# For first time pairing set key to None
CLIENT_KEY = "140cce792ae045920e14da4daa414582"

WEBOSTV_EXCEPTIONS = (
    OSError,
    ConnectionClosed,
    ConnectionClosedOK,
    ConnectionRefusedError,
    WebOsTvCommandError,
    asyncio.TimeoutError,
    asyncio.CancelledError,
)


class LG_TV:

    def __init__(self):
        self.client = None
        self._loop = asyncio.new_event_loop()
        self._listener = None

    async def on_state_change(self, client):
        """State changed callback."""
        self._listener.notify({"client" : client})
        #self._listener.notifyUser("State changed:")
        # print(f"System info: {client.system_info}")
        # print(f"Software info: {client.software_info}")
        # print(f"Hello info: {client.hello_info}")
        # print(f"Channel info: {client.channel_info}")
        # print(f"Apps: {client.apps}")
        # print(f"Inputs: {client.inputs}")
        #self._listener.notifyUser(f"Powered on: {client.power_state}")
        # print(f"App Id: {client.current_app_id}")
        # print(f"Channels: {client.channels}")
        # print(f"Current channel: {client.current_channel}")
        # print(f"Muted: {client.muted}")
        #self._listener.notifyUser(f"Volume: {client.volume}")
        # print(f"Sound output: {client.sound_output}")

    async def connect(self):
        """Webos client example."""
        client = WebOsClient(HOST, CLIENT_KEY)
        await client.register_state_update_callback(self.on_state_change)
        await client.connect()

        self.client = client

        # Store this key for future use
        self._listener.notifyUser(f"Client key: {self.client.client_key}")

        while True:
            await asyncio.sleep(5)

            now = datetime.now().strftime("%H:%M:%S")
            is_connected = client.is_connected()
            is_on = client.is_on

            System.out.println(f"[{now}] Connected: {is_connected}, Powered on: {is_on}")

            if is_connected:
                continue

            with suppress(*WEBOSTV_EXCEPTIONS):
                await client.connect()

        # await client.disconnect()
