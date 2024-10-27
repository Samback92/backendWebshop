package com.backendWebshop.backendWebshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backendWebshop.backendWebshop.model.Conversation;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
