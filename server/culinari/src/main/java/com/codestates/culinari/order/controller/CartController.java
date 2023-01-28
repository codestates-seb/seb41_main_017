package com.codestates.culinari.order.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.request.CartDelete;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.dto.response.CartResponse;
import com.codestates.culinari.order.service.CartService;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final PaginationService paginationService;

    @PostMapping
    public ResponseEntity postCart(
            @Valid @RequestBody CartPost post,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        cartService.createCart(post, principal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getCarts(
            @Min(0) @RequestParam(defaultValue = "0", required = false) int page,
            @Positive @RequestParam(defaultValue = "10", required = false) int size,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<CartResponse> pageCarts = cartService.readCarts(pageable, principal);
        List<CartResponse> carts = pageCarts.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pageCarts.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(carts, pageCarts, barNumber),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{cart-id}")
    public ResponseEntity patchCart(
            @PathVariable("cart-id") @Positive Long cartId,
            @Valid @RequestBody CartPatch patch,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        cartService.updateCart(patch, cartId, principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteCart(
            @Valid @RequestBody CartDelete delete,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        cartService.deleteCarts(delete, principal);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
