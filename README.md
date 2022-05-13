# JavaIRC Fully integrated client

JavaIRC Fully integrated client allows IRC compatible end to end encrypted P2P chat communication. Furthermore, it allows a decentralized mesh backup (although only for private chats).

Utilizing an integrated IPC server to allow direct connections, this fully integrated client stays compatible with all [RFC1459](https://www.rfc-editor.org/rfc/rfc1459.html) compatible clients and servers.

Encryption is achieved through AES GST and special CERT command to request the certificate (if supported).

## Current Problems
1. Currently only private chats between two users is end to end encrypted
2. Due to the nature of IRC, servers are inherently trusted, though much less than over traditional IRC