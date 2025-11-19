package ch.zli.m223.service;

import java.util.List;

import ch.zli.m223.model.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoomService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Room> getAllRooms() {
        return entityManager
                .createQuery("FROM Room", Room.class)
                .getResultList();
    }

    public Room getRoomById(Long id) {
        return entityManager.find(Room.class, id);
    }

    @Transactional
    public Room updateRoom(Long id, Room updatedRoom) {
        Room existingRoom = entityManager.find(Room.class, id);

        if (existingRoom != null) {
            existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
            existingRoom.setFloor(updatedRoom.getFloor());
            existingRoom.setPricePerNight(updatedRoom.getPricePerNight());
            return entityManager.merge(existingRoom);
        }

        return null;
    }

    @Transactional
    public boolean deleteRoom(Long id) {
        Room room = entityManager.find(Room.class, id);

        if (room != null) {
            entityManager.remove(room);
            return true;
        }

        return false;
    }
}