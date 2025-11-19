# API Endpunkte - Hotel Buchungssystem

## Users

| Endpunkt-URL | HTTP-Methode | Beschreibung | Benötigte Daten |
|--------------|--------------|--------------|-----------------|
| /users | GET | Ruft alle User ab | Keine |
| /users/{id} | GET | Ruft einen User nach ID ab | User-ID |
| /users | POST | Erstellt einen neuen User | firstName, lastName |
| /users/{id} | PUT | Bearbeitet einen User | User-ID, firstName, lastName |
| /users/{id} | DELETE | Löscht einen User | User-ID |

## Rooms

| Endpunkt-URL | HTTP-Methode | Beschreibung | Benötigte Daten |
|--------------|--------------|--------------|-----------------|
| /rooms | GET | Ruft alle Zimmer ab | Keine |
| /rooms/{id} | GET | Ruft ein Zimmer nach ID ab | Room-ID |
| /rooms/{id} | PUT | Bearbeitet ein Zimmer | Room-ID, roomNumber, floor, pricePerNight |
| /rooms/{id} | DELETE | Löscht ein Zimmer | Room-ID |

## Bookings

| Endpunkt-URL | HTTP-Methode | Beschreibung | Benötigte Daten |
|--------------|--------------|--------------|-----------------|
| /bookings | GET | Ruft alle Buchungen ab | Keine |
| /bookings/{id} | GET | Ruft eine Buchung nach ID ab | Booking-ID |
| /bookings/user/{userId} | GET | Ruft alle Buchungen eines Users ab | User-ID |
| /bookings/room/{roomId} | GET | Ruft alle Buchungen eines Zimmers ab | Room-ID |
| /bookings | POST | Erstellt eine neue Buchung | user {id}, room {id}, checkInDate, checkOutDate |
| /bookings/{id} | PUT | Bearbeitet eine Buchung | Booking-ID, user {id}, room {id}, checkInDate, checkOutDate |
| /bookings/{id} | DELETE | Löscht eine Buchung | Booking-ID |