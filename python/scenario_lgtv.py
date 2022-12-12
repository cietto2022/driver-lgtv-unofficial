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

    async def on_state_change(self, client):
        """State changed callback."""
        System.out.println("State changed:")
        # print(f"System info: {client.system_info}")
        # print(f"Software info: {client.software_info}")
        # print(f"Hello info: {client.hello_info}")
        # print(f"Channel info: {client.channel_info}")
        # print(f"Apps: {client.apps}")
        # print(f"Inputs: {client.inputs}")
        System.out.println(f"Powered on: {client.power_state}")
        # print(f"App Id: {client.current_app_id}")
        # print(f"Channels: {client.channels}")
        # print(f"Current channel: {client.current_channel}")
        # print(f"Muted: {client.muted}")
        System.out.println(f"Volume: {client.volume}")
        # print(f"Sound output: {client.sound_output}")

    async def connect(self):
        """Webos client example."""
        client = WebOsClient(HOST, CLIENT_KEY)
        await client.register_state_update_callback(self.on_state_change)
        await client.connect()

        self.client = client

        # Store this key for future use
        System.out.println(f"Client key: {self.client.client_key}")

        await asyncio.sleep(30)

        # await client.disconnect()
