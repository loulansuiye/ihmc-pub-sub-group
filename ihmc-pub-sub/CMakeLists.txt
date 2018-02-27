cmake_minimum_required(VERSION 3.1)
project(IHMCPubSub)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/Modules/")
set(CMAKE_BUILD_TYPE RelWithDebInfo)


set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

IF(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT)
  SET(CMAKE_INSTALL_PREFIX
    ${CMAKE_SOURCE_DIR} CACHE PATH "IHMC Pub Sub install prefix" FORCE
    )
ENDIF(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT)


find_package(JNI REQUIRED)
INCLUDE_DIRECTORIES(${JNI_INCLUDE_DIRS})

IF(APPLE)
  SET(APPLE_PATCH_COMMAND patch -N -d ${PROJECT_SOURCE_DIR}/thirdparty/Fast-RTPS/thirdparty/fastcdr/src/cpp < ${PROJECT_SOURCE_DIR}/fastcdr.patch || "${CMAKE_COMMAND}" -E echo "Patch skipped")
ELSE()
  SET(APPLE_PATCH_COMMAND "")
ENDIF()

include(ExternalProject)
SET(FAST_RTPS_INSTALL_PREFIX "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-install")
ExternalProject_Add(FastRTPS
    SOURCE_DIR "${PROJECT_SOURCE_DIR}/thirdparty/Fast-RTPS"
    BINARY_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-build"
    STAMP_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-stamp"
	DOWNLOAD_COMMAND ""
	#UPDATE_COMMAND "${CMAKE_COMMAND}" -E chdir "${PROJECT_SOURCE_DIR}" git submodule update --recursive --init "thirdparty/Fast-RTPS"
	UPDATE_COMMAND ""
	PATCH_COMMAND ${APPLE_PATCH_COMMAND}
    INSTALL_DIR "${FAST_RTPS_INSTALL_PREFIX}"
    TMP_DIR "${PROJECT_BINARY_DIR}/thirdparty/Fast-RTPS-tmp"
    CMAKE_GENERATOR "${CMAKE_GENERATOR}"
    CMAKE_ARGS
        "-DEPROSIMA_BUILD=ON"
	"-DCMAKE_BUILD_TYPE=RelWithDebInfo"
        "-DCOMPILE_EXAMPLES=OFF"
	"-DINTERNAL_DEBUG=ON"
        "-DBUILD_SHARED_LIBS=OFF"
        "-DCMAKE_INSTALL_PREFIX=${FAST_RTPS_INSTALL_PREFIX}"
        "-DCMAKE_POSITION_INDEPENDENT_CODE=true"
)
link_directories("${FAST_RTPS_INSTALL_PREFIX}/lib")
INCLUDE_DIRECTORIES("${FAST_RTPS_INSTALL_PREFIX}/include")
IF(WIN32)
	set(FastRTPS_LIBRARIES libfastcdr-1.0 libfastrtps-1.5 Iphlpapi Shlwapi)
ELSE()
	set(FastRTPS_LIBRARIES fastcdr fastrtps)
ENDIF()


add_subdirectory(cppsrc/FastRTPS)
add_subdirectory(swig/FastRTPS)
add_subdirectory(cppsrc/test)
