package com.example.api.domain.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "movements")
public class Movement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 8)
    private MovementType type;

    @Column(nullable = false)
    private Integer amount; // > 0

    @Column(name = "balance_after", nullable = false)
    private Integer balanceAfter;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "text")
    private String note;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Item getItem() { return item; } public void setItem(Item item) { this.item = item; }
    public MovementType getType() { return type; } public void setType(MovementType type) { this.type = type; }
    public Integer getAmount() { return amount; } public void setAmount(Integer amount) { this.amount = amount; }
    public Integer getBalanceAfter() { return balanceAfter; } public void setBalanceAfter(Integer balanceAfter) { this.balanceAfter = balanceAfter; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public String getNote() { return note; } public void setNote(String note) { this.note = note; }
    public Instant getCreatedAt() { return createdAt; } public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
