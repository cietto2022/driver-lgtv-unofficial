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

HOST = "192.168.11.92"
# For first time pairing set key to None
CLIENT_KEY = "140cce792ae045920e14da4daa414582"

async def on_state_change(client):
    """State changed callback."""
    print("State changed:")
    print(f"System info: {client.system_info}")
    print(f"Software info: {client.software_info}")
    print(f"Hello info: {client.hello_info}")
    print(f"Channel info: {client.channel_info}")
    print(f"Apps: {client.apps}")
    print(f"Inputs: {client.inputs}")
    print(f"Powered on: {client.power_state}")
    print(f"App Id: {client.current_app_id}")
    print(f"Channels: {client.channels}")
    print(f"Current channel: {client.current_channel}")
    print(f"Muted: {client.muted}")
    print(f"Volume: {client.volume}")
    print(f"Sound output: {client.sound_output}")

async def main():
    """Basic webOS client example."""

    client = WebOsClient(HOST, CLIENT_KEY)
    await client.register_state_update_callback(on_state_change)
    await client.connect()

    print(client.volume)
    print(client.current_channel)
    print(client.power_state)

    apps = await client.get_apps_all()

    while True:
        await asyncio.sleep(1)

        now = datetime.now().strftime("%H:%M:%S")
        is_connected = client.is_connected()
        is_on = client.is_on

        print(f"[{now}] Connected: {is_connected}, Powered on: {is_on}")

        if is_connected:
            continue

        with suppress(*WEBOSTV_EXCEPTIONS):
            await client.connect()


if __name__ == "__main__":
    asyncio.run(main())
