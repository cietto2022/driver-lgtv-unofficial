import asyncio
from contextlib import suppress
from datetime import datetime

from websockets.exceptions import ConnectionClosed, ConnectionClosedOK

from aiowebostv import WebOsClient
from aiowebostv.exceptions import WebOsTvCommandError

WEBOSTV_EXCEPTIONS = (
    OSError,
    ConnectionClosed,
    ConnectionClosedOK,
    ConnectionRefusedError,
    WebOsTvCommandError,
    asyncio.TimeoutError,
    asyncio.CancelledError,
)
class lg_tv:

    def __init__(self):
        self.HOST = "192.168.11.92"
# For first time pairing set key to None
        self.CLIENT_KEY = "140cce792ae045920e14da4daa414582"
        self._loop = asyncio.new_event_loop()
        self.client = None

    async def on_state_change(self):
        """State changed callback."""
        print("State changed:")
        print(f"System info: {self.client.system_info}")
        print(f"Software info: {self.client.software_info}")
        print(f"Hello info: {self.client.hello_info}")
        print(f"Channel info: {self.client.channel_info}")
        print(f"Apps: {self.client.apps}")
        print(f"Inputs: {self.client.inputs}")
        print(f"Powered on: {self.client.power_state}")
        print(f"App Id: {self.client.current_app_id}")
        print(f"Channels: {self.client.channels}")
        print(f"Current channel: {self.client.current_channel}")
        print(f"Muted: {self.client.muted}")
        print(f"Volume: {self.client.volume}")
        print(f"Sound output: {self.client.sound_output}")

    #async def connect(self):

    async def test(self):
        """Basic webOS client example."""

        client = WebOsClient(self.HOST, self.CLIENT_KEY)
        await client.register_state_update_callback(self.on_state_change())
        await client.connect()

        #self.client = client

        print(self.client.volume)
        print(self.client.current_channel)
        print(self.client.power_state)

        apps = await self.client.get_apps_all()

        while True:
            await asyncio.sleep(1)

            now = datetime.now().strftime("%H:%M:%S")
            is_connected = self.client.is_connected()
            is_on = self.client.is_on

            print(f"[{now}] Connected: {is_connected}, Powered on: {is_on}")

            if is_connected:
                continue

            with suppress(*WEBOSTV_EXCEPTIONS):
                await self.client.connect()
