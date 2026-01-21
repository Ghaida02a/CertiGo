////// Authentication and Authorization Utility
////// This file handles user authentication and role-based access control
////
////// User Roles
////const UserRole = {
////    ADMIN: 'ADMIN',
////    TRAINEE: 'TRAINEE'
////};
////
////// Check if user is logged in
////function isLoggedIn() {
////    return localStorage.getItem('currentUser') !== null;
////}
////
////// Get current user from localStorage
////function getCurrentUser() {
////    const userStr = localStorage.getItem('currentUser');
////    return userStr ? JSON.parse(userStr) : null;
////}
////
////// Get current user role
////function getUserRole() {
////    const user = getCurrentUser();
////    return user ? user.role : null;
////}
////
////// Check if user is admin
////function isAdmin() {
////    return getUserRole() === UserRole.ADMIN;
////}
////
////// Check if user is trainee
////function isTrainee() {
////    return getUserRole() === UserRole.TRAINEE;
////}
////
////// Save user to localStorage after login
////function saveUser(user) {
////    localStorage.setItem('currentUser', JSON.stringify(user));
////}
////
////// Logout user
////function logout() {
////    localStorage.removeItem('currentUser');
////    window.location.href = 'signin.html';
////}
////
////// Redirect to appropriate home page based on role
////function redirectToHomePage() {
////    const role = getUserRole();
////    if (role === UserRole.ADMIN) {
////        window.location.href = 'admin-index.html';
////    } else if (role === UserRole.TRAINEE) {
////        window.location.href = 'trainee-dashboard.html';
////    } else {
////        window.location.href = 'index.html';
////    }
////}
////
////// Redirect to signin if not logged in
////function requireAuth() {
////    if (!isLoggedIn()) {
////        alert('Please sign in to access this page.');
////        window.location.href = 'signin.html';
////        return false;
////    }
////    return true;
////}
////
////// Require admin role
////function requireAdmin() {
////    if (!requireAuth()) {
////        return false;
////    }
////
////    if (!isAdmin()) {
////        alert('Access Denied: This page is only accessible to administrators.');
////        redirectToHomePage();
////        return false;
////    }
////    return true;
////}
////
////// Check if user has permission for specific action
////function hasPermission(action) {
////    const role = getUserRole();
////
////    const permissions = {
////        'view_certificates': [UserRole.ADMIN, UserRole.TRAINEE],
////        'add_certificate': [UserRole.ADMIN],
////        'edit_certificate': [UserRole.ADMIN],
////        'delete_certificate': [UserRole.ADMIN],
////        'view_all_users': [UserRole.ADMIN],
////        'manage_courses': [UserRole.ADMIN],
////        'view_lessons': [UserRole.ADMIN, UserRole.TRAINEE]
////    };
////
////    return permissions[action] && permissions[action].includes(role);
////}
////
////// Hide elements based on role
////function hideElementsByRole(selector, allowedRoles) {
////    const role = getUserRole();
////    const elements = document.querySelectorAll(selector);
////
////    elements.forEach(element => {
////        if (!allowedRoles.includes(role)) {
////            element.style.display = 'none';
////        }
////    });
////}
////
////// Show elements based on role
////function showElementsByRole(selector, allowedRoles) {
////    const role = getUserRole();
////    const elements = document.querySelectorAll(selector);
////
////    elements.forEach(element => {
////        if (allowedRoles.includes(role)) {
////            element.style.display = '';
////        } else {
////            element.style.display = 'none';
////        }
////    });
////}
////
////// Update navigation based on user role
////function updateNavigationByRole() {
////    const user = getCurrentUser();
////    const role = getUserRole();
////
////    // Update account dropdown
////    const accountDropdown = document.querySelector('.dropdown');
////    if (accountDropdown && user) {
////        const dropbtn = accountDropdown.querySelector('.dropbtn');
////        const dropdownContent = accountDropdown.querySelector('.dropdown-content');
////
////        if (dropbtn) {
////            dropbtn.textContent = user.username || 'Account';
////        }
////
////        if (dropdownContent) {
////            dropdownContent.innerHTML = `
////                <a href="#" onclick="showUserProfile(); return false;">Profile</a>
////                <a href="#" onclick="logout(); return false;">Logout</a>
////            `;
////        }
////    }
////
////    // Hide/show navigation items based on role
////    if (role === UserRole.TRAINEE) {
////        // Hide admin-only links for trainees
////        hideElementsByRole('.admin-only', [UserRole.ADMIN]);
////
////        // Hide "Add Certificate" link for trainees
////        const addCertLink = document.querySelector('a[href="add-certificate.html"]');
////        if (addCertLink) {
////            addCertLink.style.display = 'none';
////        }
////    } else if (role === UserRole.ADMIN) {
////        // Show all links for admin
////        showElementsByRole('.admin-only', [UserRole.ADMIN]);
////    }
////}
////
////// Show user profile (simple alert for now)
////function showUserProfile() {
////    const user = getCurrentUser();
////    if (user) {
////        alert(`User Profile:\n\nUsername: ${user.username}\nEmail: ${user.email}\nRole: ${user.role}`);
////    }
////}
////
////// Disable buttons/actions based on role
////function disableActionsByRole() {
////    const role = getUserRole();
////
////    if (role === UserRole.TRAINEE) {
////        // Disable delete buttons for trainees
////        const deleteButtons = document.querySelectorAll('.cert-btn-delete, .btn-delete');
////        deleteButtons.forEach(btn => {
////            btn.disabled = true;
////            btn.style.opacity = '0.5';
////            btn.style.cursor = 'not-allowed';
////            btn.title = 'Only administrators can delete certificates';
////        });
////
////        // Disable add certificate button
////        const addButtons = document.querySelectorAll('a[href="add-certificate.html"]');
////        addButtons.forEach(btn => {
////            btn.style.pointerEvents = 'none';
////            btn.style.opacity = '0.5';
////            btn.title = 'Only administrators can add certificates';
////        });
////    }
////}
////
////// Initialize role-based UI on page load
////function initRoleBasedUI() {
////    // Only apply role-based UI if user is logged in
////    if (isLoggedIn()) {
////        updateNavigationByRole();
////        disableActionsByRole();
////    }
////}
////
////// Call this when DOM is loaded
////if (typeof window !== 'undefined') {
////    window.addEventListener('DOMContentLoaded', initRoleBasedUI);
////}
////
////// Export functions for use in other scripts
////if (typeof module !== 'undefined' && module.exports) {
////    module.exports = {
////        UserRole,
////        isLoggedIn,
////        getCurrentUser,
////        getUserRole,
////        isAdmin,
////        isTrainee,
////        saveUser,
////        logout,
////        redirectToHomePage,
////        requireAuth,
////        requireAdmin,
////        hasPermission,
////        hideElementsByRole,
////        showElementsByRole,
////        updateNavigationByRole,
////        disableActionsByRole,
////        initRoleBasedUI
////    };
////}
//// Authentication and Authorization Utility
//// This file handles user authentication and role-based access control
//
//// User Roles
//const UserRole = {
//    ADMIN: 'ADMIN',
//    TRAINEE: 'TRAINEE'
//};
//
//// Check if user is logged in
//function isLoggedIn() {
//    return localStorage.getItem('currentUser') !== null;
//}
//
//// Get current user from localStorage
//function getCurrentUser() {
//    const userStr = localStorage.getItem('currentUser');
//    return userStr ? JSON.parse(userStr) : null;
//}
//
//// Get current user role
//function getUserRole() {
//    const user = getCurrentUser();
//    return user ? user.role : null;
//}
//
//// Check if user is admin
//function isAdmin() {
//    return getUserRole() === UserRole.ADMIN;
//}
//
//// Check if user is trainee
//function isTrainee() {
//    return getUserRole() === UserRole.TRAINEE;
//}
//
//// Save user to localStorage after login
//function saveUser(user) {
//    localStorage.setItem('currentUser', JSON.stringify(user));
//}
//
//// Logout user
//function logout() {
//    localStorage.removeItem('currentUser');
//    window.location.href = 'signin.html';
//}
//
//// Redirect to appropriate home page based on role
//function redirectToHomePage() {
//    const role = getUserRole();
//    if (role === UserRole.ADMIN) {
//        window.location.href = 'admin-index.html';
//    } else if (role === UserRole.TRAINEE) {
//        window.location.href = 'trainee-dashboard.html';
//    } else {
//        window.location.href = 'index.html';
//    }
//}
//
//// Redirect to signin if not logged in
//function requireAuth() {
//    if (!isLoggedIn()) {
//        alert('Please sign in to access this page.');
//        window.location.href = 'signin.html';
//        return false;
//    }
//    return true;
//}
//
//// Require admin role
//function requireAdmin() {
//    if (!requireAuth()) {
//        return false;
//    }
//
//    if (!isAdmin()) {
//        alert('Access Denied: This page is only accessible to administrators.');
//        redirectToHomePage();
//        return false;
//    }
//    return true;
//}
//
//// Check if user has permission for specific action
//function hasPermission(action) {
//    const role = getUserRole();
//
//    const permissions = {
//        'view_certificates': [UserRole.ADMIN, UserRole.TRAINEE],
//        'add_certificate': [UserRole.ADMIN],
//        'edit_certificate': [UserRole.ADMIN],
//        'delete_certificate': [UserRole.ADMIN],
//        'view_all_users': [UserRole.ADMIN],
//        'manage_courses': [UserRole.ADMIN],
//        'view_lessons': [UserRole.ADMIN, UserRole.TRAINEE] // ✅ allow both roles
//    };
//
//    return permissions[action] && permissions[action].includes(role);
//}
//
//// Hide elements based on role
//function hideElementsByRole(selector, allowedRoles) {
//    const role = getUserRole();
//    const elements = document.querySelectorAll(selector);
//
//    elements.forEach(element => {
//        if (!allowedRoles.includes(role)) {
//            element.style.display = 'none';
//        }
//    });
//}
//
//// Show elements based on role
//function showElementsByRole(selector, allowedRoles) {
//    const role = getUserRole();
//    const elements = document.querySelectorAll(selector);
//
//    elements.forEach(element => {
//        if (allowedRoles.includes(role)) {
//            element.style.display = '';
//        } else {
//            element.style.display = 'none';
//        }
//    });
//}
//
//// Update navigation based on user role
//function updateNavigationByRole() {
//    const user = getCurrentUser();
//    const role = getUserRole();
//
//    // Update account dropdown
//    const accountDropdown = document.querySelector('.dropdown');
//    if (accountDropdown && user) {
//        const dropbtn = accountDropdown.querySelector('.dropbtn');
//        const dropdownContent = accountDropdown.querySelector('.dropdown-content');
//
//        if (dropbtn) {
//            dropbtn.textContent = user.username || 'Account';
//        }
//
//        if (dropdownContent) {
//            dropdownContent.innerHTML = `
//                <a href="#" onclick="showUserProfile(); return false;">Profile</a>
//                <a href="#" onclick="logout(); return false;">Logout</a>
//            `;
//        }
//    }
//
//    // Hide/show navigation items based on role
//    if (role === UserRole.TRAINEE) {
//        hideElementsByRole('.admin-only', [UserRole.ADMIN]);
//
//        // Keep lessons link visible for trainees
//        const lessonsLink = document.querySelector('a[href="get-all-lessons.html"]');
//        if (lessonsLink) {
//            lessonsLink.style.display = '';
//        }
//
//        // Hide "Add Certificate" link for trainees
//        const addCertLink = document.querySelector('a[href="add-certificate.html"]');
//        if (addCertLink) {
//            addCertLink.style.display = 'none';
//        }
//    } else if (role === UserRole.ADMIN) {
//        showElementsByRole('.admin-only', [UserRole.ADMIN]);
//    }
//}
//
//// Show user profile (simple alert for now)
//function showUserProfile() {
//    const user = getCurrentUser();
//    if (user) {
//        alert(`User Profile:\n\nUsername: ${user.username}\nEmail: ${user.email}\nRole: ${user.role}`);
//    }
//}
//
//// Disable buttons/actions based on role
//function disableActionsByRole() {
//    const role = getUserRole();
//
//    if (role === UserRole.TRAINEE) {
//        // Disable delete buttons for trainees
//        const deleteButtons = document.querySelectorAll('.cert-btn-delete, .btn-delete');
//        deleteButtons.forEach(btn => {
//            btn.disabled = true;
//            btn.style.opacity = '0.5';
//            btn.style.cursor = 'not-allowed';
//            btn.title = 'Only administrators can delete certificates';
//        });
//
//        // Disable add certificate button
//        const addButtons = document.querySelectorAll('a[href="add-certificate.html"]');
//        addButtons.forEach(btn => {
//            btn.style.pointerEvents = 'none';
//            btn.style.opacity = '0.5';
//            btn.title = 'Only administrators can add certificates';
//        });
//    }
//}
//
//// Initialize role-based UI on page load
//function initRoleBasedUI() {
//    if (isLoggedIn()) {
//        updateNavigationByRole();
//        disableActionsByRole();
//    }
//}
//
//// Call this when DOM is loaded
//if (typeof window !== 'undefined') {
//    window.addEventListener('DOMContentLoaded', initRoleBasedUI);
//}
//
//// Export functions for use in other scripts
//if (typeof module !== 'undefined' && module.exports) {
//    module.exports = {
//        UserRole,
//        isLoggedIn,
//        getCurrentUser,
//        getUserRole,
//        isAdmin,
//        isTrainee,
//        saveUser,
//        logout,
//        redirectToHomePage,
//        requireAuth,
//        requireAdmin,
//        hasPermission,
//        hideElementsByRole,
//        showElementsByRole,
//        updateNavigationByRole,
//        disableActionsByRole,
//        initRoleBasedUI
//    };
//}

// Authentication and Authorization Utility
// This file handles user authentication and role-based access control

// User Roles
const UserRole = {
    ADMIN: 'ADMIN',
    TRAINEE: 'TRAINEE'
};

// Check if user is logged in
function isLoggedIn() {
    return localStorage.getItem('currentUser') !== null;
}

// Get current user from localStorage
function getCurrentUser() {
    const userStr = localStorage.getItem('currentUser');
    return userStr ? JSON.parse(userStr) : null;
}

// Get current user role
function getUserRole() {
    const user = getCurrentUser();
    return user ? user.role : null;
}

// Check if user is admin
function isAdmin() {
    return getUserRole() === UserRole.ADMIN;
}

// Check if user is trainee
function isTrainee() {
    return getUserRole() === UserRole.TRAINEE;
}

// Save user to localStorage after login
function saveUser(user) {
    localStorage.setItem('currentUser', JSON.stringify(user));
}

// Logout user
function logout() {
    localStorage.removeItem('currentUser');
    window.location.href = 'signin.html';
}

// Redirect to appropriate home page based on role
function redirectToHomePage() {
    const role = getUserRole();
    if (role === UserRole.ADMIN) {
        window.location.href = 'admin-index.html';
    } else if (role === UserRole.TRAINEE) {
        window.location.href = 'trainee-dashboard.html';
    } else {
        window.location.href = 'index.html';
    }
}

// Redirect to signin if not logged in
function requireAuth() {
    if (!isLoggedIn()) {
        alert('Please sign in to access this page.');
        window.location.href = 'signin.html';
        return false;
    }
    return true;
}

// Require admin role
function requireAdmin() {
    if (!requireAuth()) {
        return false;
    }

    if (!isAdmin()) {
        alert('Access Denied: This page is only accessible to administrators.');
        redirectToHomePage();
        return false;
    }
    return true;
}

// Check if user has permission for specific action
function hasPermission(action) {
    const role = getUserRole();

    const permissions = {
        'view_certificates': [UserRole.ADMIN, UserRole.TRAINEE],
        'add_certificate': [UserRole.ADMIN],
        'edit_certificate': [UserRole.ADMIN],
        'delete_certificate': [UserRole.ADMIN],
        'view_all_users': [UserRole.ADMIN],
        'manage_courses': [UserRole.ADMIN],
        'view_lessons': [UserRole.ADMIN, UserRole.TRAINEE],
        'take_quiz': [UserRole.TRAINEE],
        'update_course': [UserRole.ADMIN],  // Added: Only admins can update courses
        'delete_course': [UserRole.ADMIN]   // Added: For deleting courses
    };

    return permissions[action] && permissions[action].includes(role);
}

// Hide elements based on role
function hideElementsByRole(selector, allowedRoles) {
    const role = getUserRole();
    const elements = document.querySelectorAll(selector);

    elements.forEach(element => {
        if (!allowedRoles.includes(role)) {
            element.style.display = 'none';
        }
    });
}

// Show elements based on role
function showElementsByRole(selector, allowedRoles) {
    const role = getUserRole();
    const elements = document.querySelectorAll(selector);

    elements.forEach(element => {
        if (allowedRoles.includes(role)) {
            element.style.display = '';
        } else {
            element.style.display = 'none';
        }
    });
}

// Update navigation based on user role
function updateNavigationByRole() {
    const user = getCurrentUser();
    const role = getUserRole();

    // Update account dropdown
    const accountDropdown = document.querySelector('.dropdown');
    if (accountDropdown && user) {
        const dropbtn = accountDropdown.querySelector('.dropbtn');
        const dropdownContent = accountDropdown.querySelector('.dropdown-content');

        if (dropbtn) {
            dropbtn.textContent = user.username || 'Account';
        }

        if (dropdownContent) {
            dropdownContent.innerHTML = `
                <a href="#" onclick="showUserProfile(); return false;">Profile</a>
                <a href="#" onclick="logout(); return false;">Logout</a>
            `;
        }
    }

    // Hide/show navigation items based on role
    if (role === UserRole.TRAINEE) {
        hideElementsByRole('.admin-only', [UserRole.ADMIN]);

        // Keep lessons link visible for trainees
        const lessonsLink = document.querySelector('a[href="get-all-lessons.html"]');
        if (lessonsLink) {
            lessonsLink.style.display = '';
        }

        // Hide "Add Certificate" link for trainees
        const addCertLink = document.querySelector('a[href="add-certificate.html"]');
        if (addCertLink) {
            addCertLink.style.display = 'none';
        }
    } else if (role === UserRole.ADMIN) {
        showElementsByRole('.admin-only', [UserRole.ADMIN]);
    }
}

// Show user profile (simple alert for now)
function showUserProfile() {
    const user = getCurrentUser();
    if (user) {
        alert(`User Profile:\n\nUsername: ${user.username}\nEmail: ${user.email}\nRole: ${user.role}`);
    }
}

// Disable buttons/actions based on role
function disableActionsByRole() {
    const role = getUserRole();

    if (role === UserRole.TRAINEE) {
        // Disable delete buttons for trainees
        const deleteButtons = document.querySelectorAll('.cert-btn-delete, .btn-delete');
        deleteButtons.forEach(btn => {
            btn.disabled = true;
            btn.style.opacity = '0.5';
            btn.style.cursor = 'not-allowed';
            btn.title = 'Only administrators can delete certificates';
        });

        // Disable add certificate button
        const addButtons = document.querySelectorAll('a[href="add-certificate.html"]');
        addButtons.forEach(btn => {
            btn.style.pointerEvents = 'none';
            btn.style.opacity = '0.5';
            btn.title = 'Only administrators can add certificates';
        });
    }
}

// Initialize role-based UI on page load
function initRoleBasedUI() {
    if (isLoggedIn()) {
        updateNavigationByRole();
        disableActionsByRole();
    }
}

// Call this when DOM is loaded
if (typeof window !== 'undefined') {
    window.addEventListener('DOMContentLoaded', initRoleBasedUI);
}

// Export functions for use in other scripts
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        UserRole,
        isLoggedIn,
        getCurrentUser,
        getUserRole,
        isAdmin,
        isTrainee,
        saveUser,
        logout,
        redirectToHomePage,
        requireAuth,
        requireAdmin,
        hasPermission,
        hideElementsByRole,
        showElementsByRole,
        updateNavigationByRole,
        disableActionsByRole,
        initRoleBasedUI
    };
}