package com.example.MTIXBackend.controller;

import com.example.MTIXBackend.model.Payment;
import com.example.MTIXBackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments") // Define base URL for Payment-related operations
public class PaymentController {

    private final PaymentService paymentService;

    // Inject PaymentService into the controller using constructor-based dependency injection
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments(); // Delegate to the service layer
    }

    // Get a payment by ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id); // Get payment by ID
    }

    // Create a new payment
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment); // Delegate to service for creating the payment
    }

    // Update a payment by ID
    @PutMapping("/{id}")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment); // Update the payment
    }

    // Delete a payment by ID
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id); // Delete the payment by ID
    }
}