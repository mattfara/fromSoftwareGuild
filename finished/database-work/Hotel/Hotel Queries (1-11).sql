USE Hotel;

#1. Can I identify all info needed to tell a customer their room ...?
#Even if the hotel had multiple floors? If it was a chain and had multiple hotels?
    
SELECT Room.BranchID, Room.Floor, Room_Reservation.CheckInDate, Room_Reservation.CheckOutDate, CONCAT(Person.FirstName, ' ', Person.LastName) AS CustomerName, Room.RoomNumber
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Customer
ON Reservation.CustomerID = Customer.CustomerID
INNER JOIN Person
ON Customer.PersonID = Person.PersonID
WHERE Reservation.EndDate is NOT NULL;    
    
#2. Can promotions handle a 10% increase, or a 100$ discount?
#10. Are guests allowed to use multiple promotion codes? Per reservation? Per room?

SELECT Reservation.ReservationID, Bill.BillingDate, BillDetail.BillDetailName, PromotionCode.PromotionCodeName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
INNER JOIN PromotionCode_Reservation
ON Reservation.ReservationID = PromotionCode_Reservation.ReservationID
INNER JOIN PromotionCode
ON PromotionCode.PromotionCodeID = PromotionCode_Reservation.PromotionCodeID
GROUP BY BillDetail.Charge
Having BillDetail.Charge < 0;

#3. Can I pull a bill by reservation? By room? By customer? 
#How can I print a complete invoice?

#Look At This Again
SELECT Reservation.ReservationID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Room.RoomID = 1;

SELECT Room.RoomID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Room.RoomID = 1;

# Come Back to this
SELECT Reservation.CustomerID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Reservation.CustomerID = 1;

#4.  If room #20 orders 3 bottles of champagne over 3 different days during their stay... 
#how does that appear on their bill?

SELECT * FROM Reservation;
SELECT * FROM Room_Reservation;
SELECT * FROM Room;
SELECT * FROM RoomAddOn;
SELECT * FROM RoomAddOnType;

SELECT 
    rv.ReservationID,
    r.RoomNumber as 'room number',
    rat.roomaddontypename as 'add on',
    ra.dateordered as 'date ordered'
FROM
    Reservation rv
        INNER JOIN
    Room_Reservation rr ON rv.ReservationID = rr.ReservationID
        INNER JOIN
    Room r ON r.RoomID = rr.RoomID
        INNER JOIN
    RoomAddOn ra ON rr.Room_ReservationID = ra.Room_ReservationID
        INNER JOIN
    RoomAddOnType rat ON rat.RoomAddOnTypeID = ra.RoomAddOnTypeID;
    
#5. If I decide to do a rate hike on my room service, or stop offering valet...
#how does this effect my billing & old records?

    
#6. How are rooms priced? Base rate for all? By type? By bed size, or things in it?
#Or maybe even the location in the hotel? (Kingsized bed room vs pent house)?

SELECT RoomType.RoomTypeName, RoomRate.StartDate, RoomRate.EndDate, RoomRate.Rate
FROM RoomType
INNER JOIN RoomRate
ON RoomRate.RoomTypeID = RoomType.RoomTypeID;
    
#7. What happens if I cancel a reservation?

SELECT Customer.CustomerID, Reservation.ReservationID, RoomID, StartDate DATETIME, EndDate Datetime, Cancelled
From Customer
INNER JOIN Reservation
ON Customer.CustomerID = Reservation.CustomerID
INNER JOIN Room_Reservation
ON Reservation.ReservationID = Room_Reservation.ReservationID
WHERE Room_Reservation.Cancelled = 1;

#8. Can a wedding party have many rooms on the same reservations? 
#Do they all have to arrive and leave on the same day?
USE Hotel;

DROP VIEW IF EXISTS ForAWeddingParty;
CREATE VIEW ForAWeddingParty
AS
SELECT 
    rr.ReservationID, rr.Room_ReservationID, rr.RoomID, rr.CheckInDate, rr.CheckOutDate
FROM
    Room_Reservation rr #Table 1
        INNER JOIN
    (SELECT 
        Room_Reservation.ReservationID
    FROM
        Room_Reservation
    GROUP BY ReservationID
    HAVING COUNT(Room_Reservation.ReservationID) >= 2) Table2 ON rr.ReservationID = Table2.ReservationID;
  
  Select * from ForAWeddingParty;
  
	#component queries
    
	#1 This shows that we have two reservations
    
    SELECT 
        Room_Reservation.ReservationID
    FROM
        Room_Reservation
    GROUP BY ReservationID;
    
    #2 but we only want ones that have at least two rooms associated with them
    
    SELECT 
        Room_Reservation.ReservationID
    FROM
        Room_Reservation
    GROUP BY ReservationID
    HAVING COUNT(Room_Reservation.ReservationID) >= 2;
    
    #3 The result of the above query is a table, which we join with Room_Reservation
		Select * FROM Room_Reservation;
        #this held two ReservationIDs, but we eliminated one
        
#9. Can I track 2 HD tvs in a room, or other multiple amenities? 

SELECT * FROM Room;
SELECT * FROM RoomType;
SELECT * FROM RoomAmenity;
SELECT * FROM RoomType_RoomAmenity;

SELECT 
    r.BranchID,
    r.RoomNumber,
    rt_ram.Quantity,
    ramt.RoomAmenityTypeName
FROM
    Room r
        INNER JOIN
    RoomType rt ON rt.RoomTypeID = r.RoomTypeID
        INNER JOIN
    RoomType_RoomAmenity rt_ram ON rt_ram.RoomTypeID = rt.RoomTypeID
        INNER JOIN
    RoomAmenity ram ON ram.RoomAmenityID = rt_ram.RoomAmenityID
        INNER JOIN
    RoomAmenityType ramt ON ramt.RoomAmenityTypeID = ram.RoomAmenityTypeID
WHERE
    rt_ram.Quantity > 1;

#Can the room TYPE change?

DESCRIBE Room;

SELECT 
    r.BranchID,
    r.RoomNumber,
    r.StartDate,
    IFNULL(rt.EndDate, 'Present') AS 'EndDate',
    rt.RoomTypeName
FROM
    Room r
        INNER JOIN
    RoomType rt ON r.RoomTypeID = rt.RoomTypeID
ORDER BY r.RoomNumber;

#11. Can I waive the price of an amenity or addon? 
#(AKA can I offer a free bottle of champagne or a free king bed upgrade?)