cmake_minimum_required(VERSION 3.1)
project(IHMCPubSub)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/Modules/")
set(CMAKE_BUILD_TYPE RelWithDebInfo)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

if(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT)
  set(CMAKE_INSTALL_PREFIX
    ${CMAKE_SOURCE_DIR} CACHE PATH "IHMC Pub Sub install prefix" FORCE
    )
endif(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

include(ExternalProject)
set(FAST_RTPS_INSTALL_PREFIX "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-install")
ExternalProject_Add(FastRTPS
    SOURCE_DIR "${PROJECT_SOURCE_DIR}/thirdparty/Fast-RTPS"
    BINARY_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-build"
    STAMP_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-stamp"
    DOWNLOAD_COMMAND ""
    #UPDATE_COMMAND "${CMAKE_COMMAND}" -E chdir "${PROJECT_SOURCE_DIR}" git submodule update --recursive --init "thirdparty/Fast-RTPS"
    UPDATE_COMMAND ""
    INSTALL_DIR "${FAST_RTPS_INSTALL_PREFIX}"
    TMP_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-tmp"
    CMAKE_GENERATOR "${CMAKE_GENERATOR}"
    CMAKE_ARGS
    "-DEPROSIMA_BUILD=OFF"
    "-DCMAKE_BUILD_TYPE=${CMAKE_BUILD_TYPE}"
    "-DCOMPILE_EXAMPLES=OFF"
    "-DINTERNAL_DEBUG=ON"
    "-DBUILD_SHARED_LIBS=OFF"
    "-DBUILD_TESTING=OFF"
    "-DTHIRDPARTY_fastcdr=OFF"
    "-DTINYXML2_FROM_SOURCE=ON"
    "-DCMAKE_INSTALL_PREFIX=${FAST_RTPS_INSTALL_PREFIX}"
    "-DCMAKE_POSITION_INDEPENDENT_CODE=true"
)
link_directories("${FAST_RTPS_INSTALL_PREFIX}/lib")
include_directories("${FAST_RTPS_INSTALL_PREFIX}/include")
if(WIN32)
	set(FastRTPS_LIBRARIES libfastrtps-1.5 Iphlpapi Shlwapi)
else()
	set(FastRTPS_LIBRARIES fastrtps)
endif()

add_subdirectory(cppsrc/FastRTPS)
add_subdirectory(swig/FastRTPS)
