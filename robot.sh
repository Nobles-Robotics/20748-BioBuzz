#!/bin/bash
# usage: robot setup | robot connect [ip] | robot disconnect
# written as an install-helper for homebrew/ADB

IP="${2:-192.168.43.1}"
[[ "$IP" == *:* ]] || IP="$IP:5555"

[ "$(uname -m)" = arm64 ] && BREW=/opt/homebrew/bin/brew || BREW=/usr/local/bin/brew

ADB="$HOME/Library/Android/sdk/platform-tools/adb"
[ -x "$ADB" ] || ADB="$(dirname "$BREW")/adb"

case "$1" in
  setup)
    if [ ! -x "$ADB" ]; then
      [ -x "$BREW" ] || /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
      "$BREW" install --cask android-platform-tools
    fi
    mkdir -p "$HOME/.local/bin"
    [ "$0" -ef "$HOME/.local/bin/robot" ] || cp "$0" "$HOME/.local/bin/robot"
    chmod +x "$HOME/.local/bin/robot"
    grep -qs '.local/bin' "$HOME/.zshrc" || echo 'export PATH="$HOME/.local/bin:$PATH"' >> "$HOME/.zshrc"
    echo "done."
    ;;
  connect)    "$ADB" connect "$IP" ;;
  disconnect) "$ADB" disconnect ;;
  *)          echo "usage: robot setup | robot connect [ip] | robot disconnect" ;;
esac
