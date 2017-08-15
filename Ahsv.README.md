# Ahsv.README.md
*************************************************************************
#"Ahsv" Android Studio Project Source
*************************************************************************

"Hasami-Shogi" is a familiar board game in Japan.
"Shogi" game itself requires deep-thinking more over Chess,
on the other hand, "Hasami-Shogi" is playing
among children using Shogi pieces and board.
("Hasami" is a form of Japanese verb "Hasamu"
that means "sandwich" used as like as
who sandwiches anything between two something.)

This app is not a robot but for match with your friends.
If there are two devices, you can match through wireless(Bluetooth or IP).
(You can enjoy "Chat" with your partner.)
You can select board and pieces from "Shogi"(9x9) and "Chess"(8x8).
Message is by English,
you will be soon accustomed also to Japanese gliph on Shogi pieces.
Games's objective is to capture opponent's pieces
until gameover count.
Pieces are captured when you sandwiched opponent's piece
vertically and/or horizontally,
or hold opponent's pieces at corner or border.
See "Help" for how to move and how to capture.
Entry of Bishop and/or Knight are not usual,
but optional in this variant.

   .Bluetooth connection.
      It reaches about 10m.
   .IP connection.
      Connect on private LAN with known IP address.
      Or connect by WiFi Direct it reaches about a few 10 meters,
      it is available from android 4.
   For device with NFC attachment, Bluetooth or Wi-Fi Direct connection 
   starts by closing the part of NFC tag in the range of 10 cm.
   (Note)Support of mixed use of Wi-Fi and Wi-Fi Direct depends device type.
         It may cause too slow response or disturb telephone function.

*************************************************************************
(Japanese)
# "Ahsv" Android Studio プロジェクト ソース
*************************************************************************

*** はさみ将棋 ***
ロボットではありません、対戦用アプリです
端末が２つある場合は無線(BluetoothまたはIP)で対戦
(対戦中のチャットもサポート)
盤と駒は将棋とチェスとどちらか選べます
秒読みあり、
普通のはさみ将棋と違い、オプションで(反射)角と桂も使用できます 
痛快に技を決めましょう

   .Bluetooth 接続
      １０ｍ以内の距離で接続できます
   .IP 接続
      プライベートLANに接続するか
      Android-4 以降ならWiFiダイレクトで近距離接続(数１０ｍまで届きます)、

   NFC機能つきの端末ならNFCタグの部分を10cm以内に近づけるだけで
   Bluetooth か Wi-Fiダイレクトで接続できます
   (注) WiFi と WiFiダイレクト を混用のサポートはデバイスタイプに依存します
        IP接続の応答が遅くなったり、電話機能に影響が出る可能性があります
